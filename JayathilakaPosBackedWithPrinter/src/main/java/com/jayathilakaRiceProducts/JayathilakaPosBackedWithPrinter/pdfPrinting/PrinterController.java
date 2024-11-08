package com.jayathilakaRiceProducts.JayathilakaPosBackedWithPrinter.pdfPrinting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/printer")
public class PrinterController {

    @Autowired
    private PrinterService printerService;

    @GetMapping("/print-pdf")
    public ResponseEntity<String> printPDF() {
        try {
            String pdfPath = "C:/Users/acer/Downloads/paysheet_final.pdf";
            printerService.printPDF(pdfPath);
            return ResponseEntity.ok("PDF printed successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error printing PDF: " + e.getMessage());
        }
    }
}


