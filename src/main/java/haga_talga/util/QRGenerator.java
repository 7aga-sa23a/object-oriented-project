package haga_talga.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import haga_talga.app.Main;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class QRGenerator {

    public static void promptAndGenerate() {
        Formatter.prompt("Enter Student ID: ", "blue");
        String id = Main.scanner.nextLine().trim();

        Formatter.prompt("Enter Student Name: ", "blue");
        String name = Main.scanner.nextLine().trim();

        if (id.isEmpty() || name.isEmpty()) {
            Formatter.error("ID and Name cannot be empty.");
            return;
        }

        String data = id + "|" + name;
        String fileName = id + "_" + name.replaceAll("\\s+", "_") + "_QR.png";
        Path path = Paths.get("src", "main", "resources", "qrcodes");
        
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
            Path filePath = path.resolve(fileName);
            generateQRCodeImage(data, 350, 350, filePath.toString());
            Formatter.success("QR Code generated successfully at: " + filePath.toString());
        } catch (WriterException | IOException e) {
            Formatter.error("Could not generate QR Code: " + e.getMessage());
        }
    }

    public static void generateQRCodeImage(String text, int width, int height, String filePath)
            throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }
}
