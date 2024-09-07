package com.jayathilakaRiceProducts.JayathilakaPosBackedWithPrinter.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDTO {

    private String name;
    private String position;
    private double monthly_Salary;
    private double ETF;
    private double bonus;
    private double advance_payments;
    private double loan_to_pay;
    private double loan_payment_for_month;
    private int worked_days_count;
    private int should_work_dates_total;

    public EmployeeDTO() {
    }

    public EmployeeDTO(String name, String position, double monthly_Salary, double ETF, double bonus, double advance_payments, double loan_to_pay, double loan_payment_for_month, int worked_days_count, int should_work_dates_total) {
        this.name = name;
        this.position = position;
        this.monthly_Salary = monthly_Salary;
        this.ETF = ETF;
        this.bonus = bonus;
        this.advance_payments = advance_payments;
        this.loan_to_pay = loan_to_pay;
        this.loan_payment_for_month = loan_payment_for_month;
        this.worked_days_count = worked_days_count;
        this.should_work_dates_total = should_work_dates_total;
    }
}
