package com.jayathilakaRiceProducts.JayathilakaPosBackedWithPrinter.printPdfWithTxtInsert;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.stereotype.Service;

import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * this use to add text to image
 */
@Service
public class PrinterPdfService {

    public void printPDF(String pdfPath, List<TextItem> textItems) throws Exception {
        PDDocument document = PDDocument.load(new File(pdfPath));
        PDFRenderer pdfRenderer = new PDFRenderer(document);
        BufferedImage image = pdfRenderer.renderImageWithDPI(0, 203); // 203 DPI is common for receipt printers
        document.close();

        // Add text to the image
        addTextToImage(image, textItems);

        byte[] printData = convertImageToEscPos(image);
        PrintService printer = findEpsonPrinter();
        if (printer == null) {
            throw new RuntimeException("Printer not found");
        }
        DocPrintJob job = printer.createPrintJob();
        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
        Doc doc = new SimpleDoc(printData, flavor, null);
        PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
        pras.add(new Copies(1));
        job.print(doc, pras);
    }

    private void addTextToImage(BufferedImage image, List<TextItem> textItems) {
        Graphics2D g2d = image.createGraphics();
        g2d.setColor(Color.BLACK);
        for (TextItem item : textItems) {
            g2d.setFont(new Font("Arial", Font.PLAIN, item.getFontSize()));
            g2d.drawString(item.getText(), item.getX(), item.getY());
        }
        g2d.dispose();
    }



    private byte[] convertImageToEscPos(BufferedImage image) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        // Initialize printer
        baos.write(new byte[]{0x1B, 0x40});

        int width = image.getWidth();
        int height = image.getHeight();

        // Set line spacing to 24 dots
        baos.write(new byte[]{0x1B, 0x33, 24});

        for (int y = 0; y < height; y += 24) {
            // Select bit image mode
            baos.write(new byte[]{0x1B, 0x2A, 33});
            baos.write(width % 256);
            baos.write(width / 256);

            for (int x = 0; x < width; x++) {
                for (int k = 0; k < 3; k++) {
                    byte slice = 0;
                    for (int b = 0; b < 8; b++) {
                        int yy = y + (k * 8) + b;
                        if (yy < height) {
                            Color color = new Color(image.getRGB(x, yy));
                            int grayscale = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
                            if (grayscale < 128) {
                                slice |= (byte)(1 << (7 - b));
                            }
                        }
                    }
                    baos.write(slice);
                }
            }
            baos.write(new byte[]{0x0A}); // Line feed
        }

        // Cut paper
        baos.write(new byte[]{0x1D, 0x56, 0x41, 0x10});

        return baos.toByteArray();
    }

    public PrintService findEpsonPrinter() {
        PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
        System.out.println("Found " + services.length + " print services:");
        for (PrintService service : services) {
            System.out.println("- " + service.getName());
            if (service.getName().toLowerCase().contains("tm-t81iii") ||
                    service.getName().toLowerCase().contains("epson")) {
                return service;
            }
        }
        return null;
    }
}
