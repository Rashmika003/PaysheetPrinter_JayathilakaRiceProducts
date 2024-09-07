package com.jayathilakaRiceProducts.JayathilakaPosBackedWithPrinter.model;


import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Employee {

    @Id
    @GeneratedValue
    private Integer ID;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String position; // positionEnum of office, supervisor, driver, stock_keeper, money_keeper, manager, labour

    private double monthly_Salary; // basic salary

    private double ETF;

    private double bonus;

    private double advance_payments;  // amount employees taken before the salary paying date

    private double loan_to_pay ;  // remain amount for pay

    private double loan_payment_for_month ;

    private int worked_days_count; // how many day employee has worked

    private int should_work_dates_total; // how many day employee should work

    private double calculated_salary;

    public Employee(int ID, String name, String position, double monthly_Salary, double ETF, double bonus, double advance_payments, double loan_to_pay, double loan_payment_for_month, int worked_days_count, int should_work_dates_total) {
        this.ID = ID;
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
