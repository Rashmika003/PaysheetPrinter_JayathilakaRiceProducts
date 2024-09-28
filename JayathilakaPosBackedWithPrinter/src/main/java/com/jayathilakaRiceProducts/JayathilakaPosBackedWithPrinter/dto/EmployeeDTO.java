package com.jayathilakaRiceProducts.JayathilakaPosBackedWithPrinter.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDTO {

    private String name;
    private String position; // positionEnum of office, supervisor, driver, stock_keeper, money_keeper, manager, labour
    private double monthlySalary; // basic salary
    private double ETF; // අර්ථසාදක
    private double otherAllowances; //වෙනත් දීමනා
    private double specialSupports; //විශේෂ ආධාර මුදල්
    private double advance_payments;  //අත්තිකාරම් : amount employees taken before the salary paying date
    private double other_deductions; // වෙනත් අඩු කිරීම්
    private double loan_to_pay ;  // remain amount for pay ;ගෙවිය යුතු මුළු ණය මුදල
    private double loan_payment_for_month ; //පෙර හිග මුදල්
    private double calculated_salary;
    private int worked_days_count; //මුළු වැඩ කරන දින ගණන; how many day employee has worked
    private int should_work_dates_total; //වැඩ කරන ලද මුළු දිනයන්; how many day employee should work

    public EmployeeDTO() {
    }

    public EmployeeDTO(int should_work_dates_total, int worked_days_count, double calculated_salary, double loan_payment_for_month, double loan_to_pay, double other_deductions, double advance_payments, double specialSupports, double otherAllowances, double ETF, double monthlySalary, String position, String name) {
        this.should_work_dates_total = should_work_dates_total;
        this.worked_days_count = worked_days_count;
        this.calculated_salary = calculated_salary;
        this.loan_payment_for_month = loan_payment_for_month;
        this.loan_to_pay = loan_to_pay;
        this.other_deductions = other_deductions;
        this.advance_payments = advance_payments;
        this.specialSupports = specialSupports;
        this.otherAllowances = otherAllowances;
        this.ETF = ETF;
        this.monthlySalary = monthlySalary;
        this.position = position;
        this.name = name;
    }


}
