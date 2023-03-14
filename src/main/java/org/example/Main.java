package org.example;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class Main {
    public static void main(String[] args) throws WriterException, IOException {
        String qrCodeText = "Coucou Virginie :) C'était super-sympa de travailler avec toi ta bonne humeur et ton humour vas me manquer !!\n";
        qrCodeText += "Je te souhaite une bonne continuation :D\n";
        qrCodeText += "PS: hésite pas à me faire signe si tu décides de refaire un porter de super bébés chiens ^^\n";
        qrCodeText += "PS de la plante: Salut je suis un monstera deliciosa. J'aime la lumière mais pas trop, place moi dans un endroit légèrement ombragé.\n";
        qrCodeText += "Tu n'as pas besoin de m'arroser tous les jours, 1 fois par semaine est suffisant.\n";
        qrCodeText += "J'en suis sûr nous allons bien nous entendre :D \n";
        qrCodeText += "À bientôt Emma";
        String filePath = "JD.png";
        int size = 125;
        String fileType = "png";
        File qrFile = new File(filePath);
        createQRImage(qrFile, qrCodeText, size, fileType);
        System.out.println("DONE");
    }

    private static void createQRImage(File qrFile, String qrCodeText, int size, String fileType)
            throws WriterException, IOException {
        // Create the ByteMatrix for the QR-Code that encodes the given String
        Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<>();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix byteMatrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, size, size, hintMap);
        // Make the BufferedImage that are to hold the QRCode
        int matrixWidth = byteMatrix.getWidth();
        BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
        image.createGraphics();

        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, matrixWidth, matrixWidth);
        // Paint and save the image using the ByteMatrix
        graphics.setColor(Color.BLACK);

        for (int i = 0; i < matrixWidth; i++) {
            for (int j = 0; j < matrixWidth; j++) {
                if (byteMatrix.get(i, j)) {
                    graphics.fillRect(i, j, 1, 1);
                }
            }
        }
        ImageIO.write(image, fileType, qrFile);
    }
}
