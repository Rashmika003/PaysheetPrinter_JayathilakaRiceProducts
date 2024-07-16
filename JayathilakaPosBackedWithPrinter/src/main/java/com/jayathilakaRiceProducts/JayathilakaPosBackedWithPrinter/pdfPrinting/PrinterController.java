package com.jayathilakaRiceProducts.JayathilakaPosBackedWithPrinter.pdfPrinting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/printer")
public class PrinterController {

    @Autowired
    private PrinterService printerService;

    @PostMapping("/print-pdf")
    public ResponseEntity<String> printPDF(@RequestParam String pdfPath) {
        try {
            printerService.printPDF(pdfPath);
            return ResponseEntity.ok("PDF printed successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error printing PDF: " + e.getMessage());
        }
    }
}


