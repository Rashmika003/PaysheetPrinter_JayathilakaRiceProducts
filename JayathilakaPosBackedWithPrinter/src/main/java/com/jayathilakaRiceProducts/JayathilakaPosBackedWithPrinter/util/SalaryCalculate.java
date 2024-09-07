package com.jayathilakaRiceProducts.JayathilakaPosBackedWithPrinter.util;

import com.jayathilakaRiceProducts.JayathilakaPosBackedWithPrinter.model.Employee;
import org.springframework.stereotype.Service;

@Service
public class SalaryCalculate {

    Employee employee = new Employee(
            2, "kamal", "driver", 20000, 4000, 0, 1000, 15000, 2000, 20, 28
    );

    public double finalSalaryCalculatebyEmpID(int ID){
        int totalWorkingDays = employee.getShould_work_dates_total();
        int totalDaysWorked = employee.getWorked_days_count();
        if (employee == null) {
            System.err.println("Employee shouldn't be null!!!!");
            return 0.00;
        }
        else if (totalDaysWorked == 0 || totalWorkingDays== 0){
            System.err.println("total working days in the month and total days worked cannot be 0 !!!!");
            return 0.00;
        }
        else {
            double salary = employee.getMonthly_Salary();
            double perDaySalary = salary / employee.getShould_work_dates_total();
            int leaves = employee.getShould_work_dates_total() - employee.getWorked_days_count();
            int extraWorkedDays = employee.getWorked_days_count() - employee.getShould_work_dates_total();
            // if employee has get any leaves
            if (leaves > 0) {
                salary -= (perDaySalary * leaves);
                System.out.println("cut for leaves : "+perDaySalary * leaves);
            }
            // if employee has worked extra
            if(extraWorkedDays > 0){
                salary += (perDaySalary * extraWorkedDays);
                System.out.println("extra for OT : "+perDaySalary * extraWorkedDays);

            }
            // deduct ETF
            salary -= employee.getETF();
            System.out.println("etf : "+employee.getETF());
            // deduct advances taken
            salary -= employee.getAdvance_payments();
            System.out.println("advance Payment : "+employee.getAdvance_payments());
            // add bonus
            salary += employee.getBonus();
            System.out.println("bonus : "+employee.getBonus());
            // add 'to be paid' amounts
            salary -= employee.getLoan_payment_for_month();
            System.out.println("loan payment"+employee.getLoan_payment_for_month());

            //todo : check this logic ********************************************************************
            double newLoanToPay = employee.getLoan_to_pay() - employee.getLoan_payment_for_month();

//            setLoanToPayInDBbyEmpID(ID, newLoanToPay);
//
//            setCalulatedSalaryInDBbyEmpID(ID, salary);
            return salary;
        }
    }
}
