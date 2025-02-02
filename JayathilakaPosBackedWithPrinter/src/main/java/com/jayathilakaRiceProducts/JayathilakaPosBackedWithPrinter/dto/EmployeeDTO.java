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
    private double worked_days_count; //මුළු වැඩ කරන දින ගණන; how many day employee has worked
    private double should_work_dates_total; //වැඩ කරන ලද මුළු දිනයන්; how many day employee should work
    private double extraWorkedDays;
    private int month;

    public EmployeeDTO() {
    }

    public EmployeeDTO(String name, String position, double monthlySalary, double ETF, double otherAllowances, double specialSupports, double advance_payments, double other_deductions, double loan_to_pay, double loan_payment_for_month, double calculated_salary, double worked_days_count, double should_work_dates_total, double extraWorkedDays, int month) {
        this.name = name;
        this.position = position;
        this.monthlySalary = monthlySalary;
        this.ETF = ETF;
        this.otherAllowances = otherAllowances;
        this.specialSupports = specialSupports;
        this.advance_payments = advance_payments;
        this.other_deductions = other_deductions;
        this.loan_to_pay = loan_to_pay;
        this.loan_payment_for_month = loan_payment_for_month;
        this.calculated_salary = calculated_salary;
        this.worked_days_count = worked_days_count;
        this.should_work_dates_total = should_work_dates_total;
        this.extraWorkedDays = extraWorkedDays;
        this.month = month;
    }
}
