package com.jayathilakaRiceProducts.JayathilakaPosBackedWithPrinter.service;

import org.springframework.stereotype.Service;

import javax.print.*;
import javax.print.attribute.standard.PrinterIsAcceptingJobs;
import javax.print.attribute.standard.PrinterState;
import javax.print.attribute.standard.PrinterStateReason;
import javax.print.attribute.standard.PrinterStateReasons;
import java.util.Optional;

/**
 * This is not working correctly
 */


@Service
public class ThermalPrinterStatusService {

    public record PrinterStatusResult(
            StatusCode code,
            String message,
            boolean isReady
    ) {}

    public enum StatusCode {
        READY,
        OFFLINE,
        OUT_OF_PAPER,
        COVER_OPEN,
        PAPER_JAM,
        PRINTING,
        ERROR,
        NOT_FOUND
    }

    /**
     * Finds the Epson thermal printer
     * @return Optional containing the PrintService if found, empty otherwise
     */
    public Optional<PrintService> findThermalPrinter() {
        PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);

        for (PrintService service : services) {
            String name = service.getName().toLowerCase();
            if (name.contains("tm-t81iii") || name.contains("epson")) {
                return Optional.of(service);
            }
        }

        return Optional.empty();
    }

    /**
     * Checks if the printer exists
     * @return true if printer is found, false otherwise
     */
    public boolean doesPrinterExist() {
        return findThermalPrinter().isPresent();
    }

    /**
     * Gets the detailed status of the printer
     * @return PrinterStatusResult containing status code, message, and ready state
     */
    public PrinterStatusResult getPrinterStatus() {
        Optional<PrintService> printerOptional = findThermalPrinter();

        if (printerOptional.isEmpty()) {
            return new PrinterStatusResult(
                    StatusCode.NOT_FOUND,
                    "Thermal printer not found",
                    false
            );
        }

        PrintService printer = printerOptional.get();

        // Check if printer is accepting jobs
        PrinterIsAcceptingJobs acceptingJobs =
                printer.getAttribute(PrinterIsAcceptingJobs.class);
        if (acceptingJobs == null ||
                acceptingJobs == PrinterIsAcceptingJobs.NOT_ACCEPTING_JOBS) {
            return new PrinterStatusResult(
                    StatusCode.OFFLINE,
                    "Printer is not accepting jobs",
                    false
            );
        }

        // Check printer state
        PrinterState state = printer.getAttribute(PrinterState.class);
        if (state == null) {
            return new PrinterStatusResult(
                    StatusCode.ERROR,
                    "Unable to determine printer state",
                    false
            );
        }

        // Check specific error conditions
        PrinterStateReasons stateReasons = printer.getAttribute(PrinterStateReasons.class);
        if (stateReasons != null && !stateReasons.isEmpty()) {
            Optional<PrinterStatusResult> errorStatus = checkErrorConditions(stateReasons);
            if (errorStatus.isPresent()) {
                return errorStatus.get();
            }
        }

        // Check if printer is currently printing
        if (state == PrinterState.PROCESSING) {
            return new PrinterStatusResult(
                    StatusCode.PRINTING,
                    "Printer is currently printing",
                    false
            );
        }

        // If we get here, the printer is ready
        return new PrinterStatusResult(
                StatusCode.READY,
                "Printer is ready",
                true
        );
    }

    /**
     * Checks for specific error conditions based on printer state reasons
     * @param stateReasons the PrinterStateReasons to check
     * @return Optional containing PrinterStatusResult if an error is found, empty otherwise
     */
    private Optional<PrinterStatusResult> checkErrorConditions(PrinterStateReasons stateReasons) {
        for (PrinterStateReason reason : stateReasons.keySet()) {
            if (reason.toString().contains("MEDIA_EMPTY")) {
                return Optional.of(new PrinterStatusResult(
                        StatusCode.OUT_OF_PAPER,
                        "Printer is out of paper",
                        false
                ));
            }

            if (reason.toString().contains("DOOR_OPEN") ||
                    reason.toString().contains("COVER_OPEN")) {
                return Optional.of(new PrinterStatusResult(
                        StatusCode.COVER_OPEN,
                        "Printer cover is open",
                        false
                ));
            }

            if (reason.toString().contains("MEDIA_JAM")) {
                return Optional.of(new PrinterStatusResult(
                        StatusCode.PAPER_JAM,
                        "Paper jam detected",
                        false
                ));
            }
        }

        return Optional.empty();
    }

    /**
     * Gets a human-readable description of all printer state reasons
     * @param printer the PrintService to check
     * @return A string containing all printer state reasons
     */
    public String getAllPrinterStateReasons(PrintService printer) {
        PrinterStateReasons stateReasons = printer.getAttribute(PrinterStateReasons.class);
        if (stateReasons == null || stateReasons.isEmpty()) {
            return "No state reasons reported";
        }

        StringBuilder reasons = new StringBuilder();
        for (PrinterStateReason reason : stateReasons.keySet()) {
            reasons.append(reason.toString()).append(", ");
        }
        return reasons.substring(0, reasons.length() - 2);
    }

    /**
     * Checks if the printer is ready to print
     * @return true if printer is ready, false otherwise
     */
    public boolean isPrinterReady() {
        return getPrinterStatus().isReady();
    }

    /**
     * Gets all available print services for debugging
     * @return Array of print service names
     */
    public String[] getAvailablePrinters() {
        PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
        return java.util.Arrays.stream(services)
                .map(PrintService::getName)
                .toArray(String[]::new);
    }
}