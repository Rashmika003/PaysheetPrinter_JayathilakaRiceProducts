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
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String position; // positionEnum of office, supervisor, driver, stock_keeper, money_keeper, manager, labour

    private double monthlySalary; // basic salary

    private double ETF; // අර්ථසාදක

    private double otherAllowances; //වෙනත් දීමනා

    private double specialSupports; //විශේෂ ආධාර :මුදල්

    private double advance_payments;  //අත්තිකාරම් : amount employees taken before the salary paying date

    private double other_deductions; // වෙනත් අඩු කිරීම්

    private double loan_to_pay ;  // remain amount for pay ;ගෙවිය යුතු මුළු ණය මුදල

    private double loan_payment_for_month ; //පෙර හිග මුදල්

    private int worked_days_count; //මුළු වැඩ කරන දින ගණන; how many day employee has worked

    private int should_work_dates_total; //වැඩ කරන ලද මුළු දිනයන්; how many day employee should work

    private double calculated_salary; //

    private int extraWorkedDays; // අමතර වැඩකල දින ගණන : store for printing purpose
    private double extraWorkedDaysValue; // අමතර වැඩකල දින ගණන value : store for printing purpose

    private double roughSalary; // දල වැටුප : store for printing purpose

    private int extraHolidays; //අමතර නිවාඩු දින : store for printing purpose
    private double extraHolidaysValue; //අමතර නිවාඩු දින value eka : store for printing purpose

    private int month; // this is the month salary calculating (yah yah... this way of doing this is not good.)

    public Employee(String name, String position, double monthlySalary, double ETF, double otherAllowances, double specialSupports, double advance_payments, double other_deductions, double loan_to_pay, double loan_payment_for_month, int worked_days_count, int should_work_dates_total, double calculated_salary, int extraWorkedDays, double roughSalary) {
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
        this.worked_days_count = worked_days_count;
        this.should_work_dates_total = should_work_dates_total;
        this.calculated_salary = calculated_salary;
        this.extraWorkedDays = extraWorkedDays;
        this.roughSalary = roughSalary;
    }
}
