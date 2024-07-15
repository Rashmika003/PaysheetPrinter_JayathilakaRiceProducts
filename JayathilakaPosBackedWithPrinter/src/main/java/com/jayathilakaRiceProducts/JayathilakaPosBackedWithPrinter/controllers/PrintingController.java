package com.jayathilakaRiceProducts.JayathilakaPosBackedWithPrinter.controllers;


import com.jayathilakaRiceProducts.JayathilakaPosBackedWithPrinter.service.PrintingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/print")
public class PrintingController {

    @Autowired
    private PrintingService printingService;

    @GetMapping
    public String printText(@RequestParam String text) {
        try {
            printingService.print(text);
            return "Print job sent successfully.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Printing failed: " + e.getMessage();
        }
    }
}

