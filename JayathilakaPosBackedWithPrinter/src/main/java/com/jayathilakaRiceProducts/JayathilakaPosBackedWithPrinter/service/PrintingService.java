package com.jayathilakaRiceProducts.JayathilakaPosBackedWithPrinter.service;

import org.jpos.util.Logger;
import org.jpos.util.SimpleLogListener;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.io.PrintWriter;
import javax.print.*;
import java.util.Arrays;

@Service
public class PrintingService {

    public void print(String text) throws Exception {
        // Look up for a USB printer
        PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
        PrintService epsonPrinter = null;

        for (PrintService service : services) {
            if (service.getName().toLowerCase().contains("tm-t81iii") ||
                    service.getName().toLowerCase().contains("epson")) {
                epsonPrinter = service;
                break;
            }
        }

        if (epsonPrinter == null) {
            throw new Exception("Epson TM-T81III printer not found.");
        }

        // Send text to printer using jPOS
        Logger logger = new Logger();
        logger.addListener(new SimpleLogListener(System.out));

        OutputStream os = null;
        try {
            DocPrintJob job = epsonPrinter.createPrintJob();
            DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;

            // ESC/POS commands
            byte[] initPrinter = {0x1B, 0x40}; // Initialize printer
            byte[] textBytes = (text + "\n").getBytes(); // Text to print
            byte[] cutPaper = {0x1D, 0x56, 0x41}; // Full cut

            byte[] printData = new byte[initPrinter.length + textBytes.length + cutPaper.length];
            System.arraycopy(initPrinter, 0, printData, 0, initPrinter.length);
            System.arraycopy(textBytes, 0, printData, initPrinter.length, textBytes.length);
            System.arraycopy(cutPaper, 0, printData, initPrinter.length + textBytes.length, cutPaper.length);

            Doc doc = new SimpleDoc(printData, flavor, null);
            job.print(doc, null);
        } catch (Exception e) {
            throw new Exception("Failed to print: " + e.getMessage(), e);
        } finally {
            if (os != null) {
                os.close();
            }
        }
    }
}

