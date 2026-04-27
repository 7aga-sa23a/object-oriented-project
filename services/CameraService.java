package services;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class CameraService {
    private Webcam webcam;
    private JPanel cameraPanel;
    private JFrame previewFrame;
    private boolean mirrorImage;
    private volatile BufferedImage currentFrame;

    public CameraService() {
        this.webcam = null;
        this.cameraPanel = null;
        this.previewFrame = null;
        this.mirrorImage = true;
        this.currentFrame = null;
    }

    public boolean openCamera() {
        closeCamera();
        if (!hasCamera()) {
            System.out.println("No webcam found!");
            return false;
        }

        webcam = Webcam.getDefault();
        if (webcam == null) {
            System.out.println("Cannot access webcam!");
            return false;
        }

        webcam.setViewSize(WebcamResolution.VGA.getSize());
        webcam.open();

        cameraPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                BufferedImage frame = currentFrame;
                if (frame != null) {
                    Graphics2D g2d = (Graphics2D) g;
                    int w = getWidth();
                    int h = getHeight();
                    if (mirrorImage) {
                        g2d.drawImage(frame, w, 0, -w, h, null);
                    } else {
                        g2d.drawImage(frame, 0, 0, w, h, null);
                    }
                }
            }
        };
        cameraPanel.setPreferredSize(new java.awt.Dimension(640, 480));
        cameraPanel.setBackground(java.awt.Color.BLACK);

        previewFrame = new JFrame("QR Scanner");
        previewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        previewFrame.add(cameraPanel, BorderLayout.CENTER);
        previewFrame.pack();
        previewFrame.setLocationRelativeTo(null);
        previewFrame.setAlwaysOnTop(true);
        previewFrame.setResizable(false);
        previewFrame.setVisible(true);

        Thread captureThread = new Thread(() -> {
            while (webcam != null && webcam.isOpen()) {
                try {
                    currentFrame = webcam.getImage();
                    if (cameraPanel != null && currentFrame != null) {
                        cameraPanel.repaint();
                    }
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
        captureThread.start();

        System.out.println("Camera opened successfully!");
        return true;
    }

    public void closeCamera() {
        if (previewFrame != null) {
            previewFrame.dispose();
            previewFrame = null;
        }
        if (webcam != null && webcam.isOpen()) {
            webcam.close();
        }
        webcam = null;
        cameraPanel = null;
        currentFrame = null;
    }

    public BufferedImage getCurrentFrame() {
        return currentFrame;
    }

    public boolean isCameraOpen() {
        return webcam != null && webcam.isOpen();
    }

    public boolean hasCamera() {
        List<Webcam> webcams = Webcam.getWebcams();
        return webcams != null && !webcams.isEmpty();
    }

    public static List<Webcam> getAvailableCameras() {
        return Webcam.getWebcams();
    }
}