package com.jayathilakaRiceProducts.JayathilakaPosBackedWithPrinter.service;

import com.jayathilakaRiceProducts.JayathilakaPosBackedWithPrinter.model.Employee;
import com.jayathilakaRiceProducts.JayathilakaPosBackedWithPrinter.printPdfWithTxtInsert.PrinterPdfService;
import com.jayathilakaRiceProducts.JayathilakaPosBackedWithPrinter.printPdfWithTxtInsert.TextItem;
import com.jayathilakaRiceProducts.JayathilakaPosBackedWithPrinter.repository.EmployeeRepository;
import com.jayathilakaRiceProducts.JayathilakaPosBackedWithPrinter.util.SalaryCalculate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaysheetPrinterService {

    private final EmployeeRepository employeeRepository;
    private final PrinterPdfService printerPdfService;

//    @Value("${pdfLocationAbsPath}")
//    private String pdfPath;

    private String pdfPath = "/home/rashmika/Paid Projects/JayathilakaRiceProducts_With printer/PaysheetPrinter_JayathilakaRiceProducts/paysheet_template_bold.pdf";

    /**
     * steps:
     * 1. get the employee
     * 2. check the printer status
     * 3. print the receipt
     * todo : does salary has calculated before? or calculate
     */
    public ResponseEntity<?> printEmpRecipt(@PathVariable int id){

        try{
            // ------------ get the employee -----------------
            Optional<Employee> employeeOpt = employeeRepository.findById(id);
            Employee employee;
            if (employeeOpt.isPresent()) {
                employee = employeeOpt.get();
            }
            else {
                return new ResponseEntity<>(
                        "Employee not found",
                        HttpStatus.NOT_ACCEPTABLE
                );
            }

            List<TextItem> textItems = new ArrayList<>();
//            textItems.add(new TextItem("{100, 100, 15}", 100, 100, 15));
//            textItems.add(new TextItem("{150, 200, 15}", 150, 200, 15));
//            textItems.add(new TextItem("{200, 300, 15}", 200, 300, 15));
//            textItems.add(new TextItem("{250, 400, 15}", 250, 400, 15));
//            textItems.add(new TextItem("{300, 500, 15}", 300, 500, 15));
//            textItems.add(new TextItem("{350, 600, 15}", 300, 600, 15));
//            textItems.add(new TextItem("{400, 700, 15}", 400, 700, 15));

            //textItems.add(new TextItem(employee.getName(), 400, 100, 35));

            // month
            textItems.add(new TextItem("05", 240, 70, 35));

            //date
            LocalDate currentDate = LocalDate.now();
            String defaultFormat = currentDate.toString();
            textItems.add(new TextItem(defaultFormat, 130, 110, 35));

            //name
            textItems.add(new TextItem("Gayanthi Madhushika", 130, 150, 35));

            //monthly salary
            textItems.add(new TextItem("20,000", 250, 190, 35));

            //other offers
            textItems.add(new TextItem("2,990", 250, 235, 35));

            //special support
            textItems.add(new TextItem("5,980", 300, 275, 35));

            //extra worked days
            textItems.add(new TextItem("2", 400, 315, 35));

            //rought salary
            textItems.add(new TextItem("35,790", 200, 355, 35));

            //eft
            textItems.add(new TextItem("6,900", 250, 440, 35));

            //advance
            textItems.add(new TextItem("7,000", 250, 480, 35));

            //extra off days
            textItems.add(new TextItem("4", 310, 520, 35));

            //other deductions
            textItems.add(new TextItem("3,400", 310, 560, 35));

            //loan payment for month
            textItems.add(new TextItem("15,000", 300, 610, 35));

            //remain salary
            textItems.add(new TextItem("30,900", 250, 650, 35));

            //loan to pay
            textItems.add(new TextItem("14,324", 400, 730, 35));



            printerPdfService.printPDF(pdfPath, textItems);

            return new ResponseEntity<>(
                    "OK",
                    HttpStatus.OK
            );


        }
        catch(Exception e){
            return new ResponseEntity<>(
                    "Un-Expected Error",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }



    }



}
