package com.jayathilakaRiceProducts.JayathilakaPosBackedWithPrinter.printPdfWithTxtInsert;

import com.jayathilakaRiceProducts.JayathilakaPosBackedWithPrinter.pdfPrinting.PrinterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * this is the working one
 */

@RestController
@RequestMapping("/api/printer")
public class PrinterPDFController {

    @Autowired
    private PrinterPdfService printerPdfService;

    @GetMapping("/print-text-with-text")
    public ResponseEntity<String> printPDF(@RequestParam String pdfPath) {
        try {

            List<TextItem> textItems = new ArrayList<>();
//            textItems.add(new TextItem("{100, 400, 15}", 100, 400, 15));
//            textItems.add(new TextItem("{100, 500, 15}", 100, 500, 15));
//            textItems.add(new TextItem("{100, 600, 15}", 100, 600, 15));
//            textItems.add(new TextItem("{100, 700, 15}", 100, 700, 15));
//            textItems.add(new TextItem("{100, 800, 15}", 100, 800, 15));
//            textItems.add(new TextItem("{100, 900, 15}", 100, 900, 15));
//            textItems.add(new TextItem("{100, 1000, 15}", 100, 1000, 15));


//            textItems.add(new TextItem("{}", 150, 200, 15));



            printerPdfService.printPDF(pdfPath, textItems);
            return ResponseEntity.ok("PDF printed successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error printing PDF: " + e.getMessage());
        }
    }
}
