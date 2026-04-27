package services;

import models.Student;
import com.google.zxing.*;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;

import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;

public class QRScannerService {
    private final CameraService cameraService;
    private final MultiFormatReader reader;
    private final Set<String> scannedIds;
    private boolean running;
    private boolean paused;
    private Student lastScannedStudent;
    private static final int SCAN_DELAY = 300;

    public QRScannerService(CameraService cameraService) {
        this.cameraService = cameraService;
        this.reader = new MultiFormatReader();
        this.scannedIds = new HashSet<>();
        this.running = false;
        this.paused = false;
        this.lastScannedStudent = null;
    }

    public void startScanning() {
        if (!cameraService.isCameraOpen()) {
            System.out.println("Camera not open!");
            return;
        }
        running = true;
        paused = false;
        scannedIds.clear();
        Thread scanThread = new Thread(this::scanLoop);
        scanThread.start();
    }

    private void scanLoop() {
        while (running) {
            if (paused || !cameraService.isCameraOpen()) {
                sleep(100);
                continue;
            }
            try {
                BufferedImage image = cameraService.getCurrentFrame();
                if (image == null) {
                    sleep(SCAN_DELAY);
                    continue;
                }
                Result result = decodeQR(image);
                if (result != null) {
                    String qrData = result.getText();
                    System.out.println("[DEBUG] QR Found: " + qrData);
                    Student student = Student.fromQRData(qrData);
                    if (student != null && !scannedIds.contains(student.getId())) {
                        scannedIds.add(student.getId());
                        lastScannedStudent = student;
                        System.out.println("[SCANNED] " + student);
                    }
                }
                sleep(SCAN_DELAY);
            } catch (Exception e) {
                System.out.println("[ERROR] " + e.getMessage());
                sleep(SCAN_DELAY);
            }
        }
    }

    private Result decodeQR(BufferedImage image) {
        try {
            int width = image.getWidth();
            int height = image.getHeight();
            if (width <= 0 || height <= 0) return null;
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            return reader.decode(bitmap);
        } catch (NotFoundException e) {
            return null;
        } catch (Exception e) {
            System.out.println("[DECODE ERROR] " + e.getMessage());
            return null;
        }
    }

    public void stopScanning() {
        running = false;
    }

    public boolean isRunning() {
        return running;
    }

    public Student getLastScannedStudent() {
        return lastScannedStudent;
    }

    public void clearLastScanned() {
        lastScannedStudent = null;
    }

    public int getScannedCount() {
        return scannedIds.size();
    }

    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}