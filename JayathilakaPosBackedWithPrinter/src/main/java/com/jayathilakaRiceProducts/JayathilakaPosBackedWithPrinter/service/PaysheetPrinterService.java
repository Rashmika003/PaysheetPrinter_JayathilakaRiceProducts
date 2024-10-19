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

import java.text.DecimalFormat;
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

    private static final DecimalFormat CURRENCY_FORMAT = new DecimalFormat("#,##0.00");

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

            // add test grid to the recept
//            textItems.add(new TextItem("{100, 100, 15}", 100, 100, 15));
//            textItems.add(new TextItem("{150, 200, 15}", 150, 200, 15));
//            textItems.add(new TextItem("{200, 300, 15}", 200, 300, 15));
//            textItems.add(new TextItem("{250, 400, 15}", 250, 400, 15));
//            textItems.add(new TextItem("{300, 500, 15}", 300, 500, 15));
//            textItems.add(new TextItem("{350, 600, 15}", 300, 600, 15));
//            textItems.add(new TextItem("{400, 700, 15}", 400, 700, 15));

            //textItems.add(new TextItem(employee.getName(), 400, 100, 35));

            // ************ adding real fields *******************

            // month
            textItems.add(new TextItem("05", 240, 70, 35));

            //date
            LocalDate currentDate = LocalDate.now();
            String defaultFormat = currentDate.toString();
            textItems.add(new TextItem(defaultFormat, 130, 110, 35));

            //name
            textItems.add(new TextItem(employee.getName(), 130, 150, 35));

            //monthly salary
            textItems.add(new TextItem(formatCurrency(employee.getMonthlySalary()), 300, 190, 35));

            //other offers
            textItems.add(new TextItem(formatCurrency(employee.getOtherAllowances()), 300, 235, 35));

            //special support
            textItems.add(new TextItem(formatCurrency(employee.getSpecialSupports()), 300, 275, 35));

            //extra worked days
            textItems.add(new TextItem(employee.getExtraWorkedDays()+"", 400, 315, 35));

            //rough salary
            textItems.add(new TextItem(formatCurrency(employee.getRoughSalary()), 300, 355, 35));

            //etf
            textItems.add(new TextItem(formatCurrency(employee.getETF()), 310, 440, 35));

            //advance
            textItems.add(new TextItem(formatCurrency(employee.getAdvance_payments()), 310, 480, 35));

            //extra off days
            textItems.add(new TextItem(employee.getExtraHolidays() + "", 310, 520, 35));

            //other deductions
            textItems.add(new TextItem(formatCurrency(employee.getOther_deductions()), 310, 560, 35));

            //loan payment for month
            textItems.add(new TextItem(formatCurrency(employee.getLoan_payment_for_month()), 310, 610, 35));

            //remain salary
            textItems.add(new TextItem(formatCurrency(employee.getCalculated_salary()), 310, 650, 35));

            //loan to pay
            textItems.add(new TextItem(formatCurrency(employee.getLoan_to_pay()), 375, 730, 35));

            // if printer not found
            if(printerPdfService.findEpsonPrinter() == null){
                return new ResponseEntity<>(
                        "Printer Not Found",
                        HttpStatus.NOT_ACCEPTABLE
                );
            }

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

    private static String formatCurrency(double value) {
        return CURRENCY_FORMAT.format(value);
    }


}
