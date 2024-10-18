package com.jayathilakaRiceProducts.JayathilakaPosBackedWithPrinter.controllers;


import com.jayathilakaRiceProducts.JayathilakaPosBackedWithPrinter.service.PaysheetPrinterService;
import com.jayathilakaRiceProducts.JayathilakaPosBackedWithPrinter.service.PrintingService;
import com.jayathilakaRiceProducts.JayathilakaPosBackedWithPrinter.service.ThermalPrinterStatusService;
import lombok.RequiredArgsConstructor;
import org.jpos.iso.ISOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/printer")
@RequiredArgsConstructor
public class PrintingController {

    private final PrintingService printerService;
    private final PaysheetPrinterService paysheetPrinterService;
    private final ThermalPrinterStatusService thermalPrinterStatusService;

    @GetMapping("/test")
    public ResponseEntity<String> print() {
        try {
//            printerService.printWithDifferentSizes(
//                    "this is a normal size",
//                    "this is the large text",
//                    "this is the small size text"
//            );
            printerService.printSinhalaText("මෙය පෙළ මුද්\u200Dරණයකි");
//            printerService.printSinhala("මෙය පෙළ මුද්\\u200Dරණයකි");
            return ResponseEntity.ok("Printed successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error printing: " + e.getMessage());
        }
    }

    @GetMapping("/paysheet-print/{id}")
    public ResponseEntity<?> printEmpRecipt(@PathVariable int id){
        return paysheetPrinterService.printEmpRecipt(id);
    }

    @GetMapping("/get-printer-status")
    public ResponseEntity<?> getPrinterStatus(){
        return new ResponseEntity<>(
                thermalPrinterStatusService.getPrinterStatus(),
                HttpStatus.OK
        );

    }



}

