package com.jayathilakaRiceProducts.JayathilakaPosBackedWithPrinter.controllers;


import com.jayathilakaRiceProducts.JayathilakaPosBackedWithPrinter.service.PrintingService;
import org.jpos.iso.ISOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/print")
public class PrintingController {

    @Autowired
    private PrintingService printerService;

    @GetMapping
    public ResponseEntity<String> print() {
        try {
//            printerService.printWithDifferentSizes(
//                    "this is a normal size",
//                    "this is the large text",
//                    "this is the small size text"
//            );
//            printerService.printSinhalaText("මෙය පෙළ මුද්\u200Dරණයකි");
            printerService.printSinhala("මෙය පෙළ මුද්\\u200Dරණයකි");
            return ResponseEntity.ok("Printed successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error printing: " + e.getMessage());
        }
    }
}

