package com.jayathilakaRiceProducts.JayathilakaPosBackedWithPrinter.service;

import org.jpos.iso.BaseChannel;
import org.jpos.iso.ISOException;
import org.jpos.util.Logger;
import org.jpos.util.NameRegistrar;
import org.jpos.util.SimpleLogListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaPrintableArea;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Service
public class PrintingService {


    private PrintService findEpsonPrinter() {
        PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
        for (PrintService service : services) {
            if (service.getName().toLowerCase().contains("tm-t81iii") ||
                    service.getName().toLowerCase().contains("epson")) {
                return service;
            }
        }
        throw new RuntimeException("Epson TM-T81III printer not found");
    }

    public void printSinhalaText(String sinhalaText) {
        try {
            PrintService epsonPrinter = findEpsonPrinter();

            // ESC/POS commands for initializing the printer
            String initPrinter = "\u001b@"; // Initialize printer
            String cutPaper = "\u001d\u0056\u0041"; // Full cut

            // Combine commands with the Sinhala text
            StringBuilder printData = new StringBuilder();
            printData.append(initPrinter);
            printData.append(sinhalaText);
            printData.append("\n\n\n\n"); // Feed lines
            printData.append(cutPaper);

            DocPrintJob job = epsonPrinter.createPrintJob();
            DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
            byte[] printBytes = printData.toString().getBytes(StandardCharsets.UTF_8);
            Doc doc = new SimpleDoc(printBytes, flavor, null);

            job.print(doc, null);

        } catch (PrintException e) {
            throw new RuntimeException("Error printing: " + e.getMessage(), e);
        }
    }

    public void printSinhala(String sinhalaText) {
        try {
            PrintService epsonPrinter = findEpsonPrinter();

            BufferedImage image = createSinhalaImage(sinhalaText);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            // Initialize printer
            outputStream.write(new byte[]{0x1B, 0x40}); // ESC @ - Initialize

            // Print image data
            ImageIO.write(image, "PNG", outputStream);

            // Feed a few lines to ensure all text is printed before cutting
            outputStream.write("\n\n\n\n".getBytes("ISO-8859-1"));

            // Cut paper
            outputStream.write(new byte[]{0x1D, 0x56, 0x00}); // GS V 0 - Full cut

            byte[] printData = outputStream.toByteArray();
            outputStream.close();

            DocPrintJob job = epsonPrinter.createPrintJob();
            DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
            Doc doc = new SimpleDoc(printData, flavor, null);

            job.print(doc, null);

        } catch (PrintException | IOException e) {
            throw new RuntimeException("Error printing Sinhala: " + e.getMessage(), e);
        }
    }

    private BufferedImage createSinhalaImage(String text) throws IOException {
        BufferedImage image = new BufferedImage(300, 100, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        Font font = new Font("Iskoola Pota", Font.PLAIN, 24); // Example font that supports Sinhala
        g2d.setFont(font);
        g2d.drawString(text, 10, 30);
        g2d.dispose();
        return image;
    }


}



