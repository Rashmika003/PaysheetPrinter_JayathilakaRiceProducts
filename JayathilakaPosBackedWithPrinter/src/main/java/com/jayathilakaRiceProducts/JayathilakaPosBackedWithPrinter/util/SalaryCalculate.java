package com.jayathilakaRiceProducts.JayathilakaPosBackedWithPrinter.util;

import com.jayathilakaRiceProducts.JayathilakaPosBackedWithPrinter.model.Employee;
import com.jayathilakaRiceProducts.JayathilakaPosBackedWithPrinter.repository.EmployeeRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SalaryCalculate {

    private final EmployeeRepository employeeRepository;

    /**
     * @Usage use to calculate final salary for a employee
     */
    public void finalSalaryCalulator(Integer id){

        Optional<Employee> employeeOpt = employeeRepository.findById(id);
        Employee employee;
        if (employeeOpt.isPresent()){
            employee = employeeOpt.get();
        }else {
            return;
        }

        int totalWorkingDays = employee.getShould_work_dates_total(); //මුළු වැඩ කරන දින ගණන
        int totalDaysWorked = employee.getWorked_days_count(); //වැඩ කරන ලද මුළු දිනයන්
        double salary = employee.getMonthlySalary();

        if(totalDaysWorked == totalWorkingDays){
            employee.setExtraWorkedDaysValue(0);
            employee.setExtraHolidaysValue(0);
        }

        // calculate dates
        double perDaySalary = salary / totalWorkingDays;
        log.info("[SalaryCalculate : finalSalaryCalulator] perDaySalary : {}", perDaySalary);
        int leaves = totalWorkingDays - totalDaysWorked;
        int extraWorkedDays = totalDaysWorked - totalWorkingDays;

        // calculate rough salary
        double roughSalary = salary + employee.getOtherAllowances() + employee.getSpecialSupports();

        // if employee has worked extra add them to rough salary
        if(extraWorkedDays > 0){
            roughSalary += (perDaySalary * extraWorkedDays);

            employee.setExtraWorkedDaysValue(perDaySalary * extraWorkedDays);  // add to db for bill print
            employee.setExtraHolidaysValue(0);

            log.info("[SalaryCalculate : finalSalaryCalulator] perDaySalary * extraWorkedDays : {}", perDaySalary * extraWorkedDays);
            log.info("[SalaryCalculate : finalSalaryCalulator] rough salary with extra days : {}", roughSalary);
            employee.setRoughSalary(roughSalary);
            employee.setExtraWorkedDays(extraWorkedDays);
        }else {
            employee.setRoughSalary(roughSalary);
            employee.setExtraWorkedDays(0);
            log.info("[SalaryCalculate : finalSalaryCalulator] rough salary without extra days: {}", roughSalary);
        }

        // calculate reductions for salary
        double reductions = employee.getETF() +
                employee.getAdvance_payments() +
                employee.getOther_deductions() +
                employee.getLoan_payment_for_month();
        log.info("[SalaryCalculate : finalSalaryCalulator] reductions with out calc leaves : {}", reductions);

        if (leaves > 0) {
            reductions += (perDaySalary * leaves);

            employee.setExtraHolidaysValue(perDaySalary * leaves);
            employee.setExtraWorkedDaysValue(0);

            employee.setExtraHolidays(leaves);
            log.info("[SalaryCalculate : finalSalaryCalulator] reductions with leaves : {}", reductions);
        }
        else {
            employee.setExtraHolidays(0);
            log.info("[SalaryCalculate : finalSalaryCalulator] reductions without leaves : {}", reductions);
        }

        double finalSalary = roughSalary - reductions;
        employee.setCalculated_salary(finalSalary);
        log.info("[SalaryCalculate : finalSalaryCalulator] calculated salary : {}", finalSalary);

        // calculate loan to pay : ගෙවිය යුතු මුළු ණය මුදල
        double loanToPay = employee.getLoan_to_pay() - employee.getLoan_payment_for_month();
        employee.setLoan_to_pay(loanToPay);
        log.info("[SalaryCalculate : finalSalaryCalulator] new total loan : {}", loanToPay);

        employeeRepository.save(employee);

    }

//    Employee employee = new Employee(
//            2, "kamal", "driver", 20000, 4000, 0, 1000, 15000, 2000, 20, 28
//    );

//    public double finalSalaryCalculatebyEmpID(int ID){
//        int totalWorkingDays = employee.getShould_work_dates_total();
//        int totalDaysWorked = employee.getWorked_days_count();
//        if (employee == null) {
//            System.err.println("Employee shouldn't be null!!!!");
//            return 0.00;
//        }
//        else if (totalDaysWorked == 0 || totalWorkingDays== 0){
//            System.err.println("total working days in the month and total days worked cannot be 0 !!!!");
//            return 0.00;
//        }
//        else {
//            double salary = employee.getMonthly_Salary();
//            double perDaySalary = salary / employee.getShould_work_dates_total();
//            int leaves = employee.getShould_work_dates_total() - employee.getWorked_days_count();
//            int extraWorkedDays = employee.getWorked_days_count() - employee.getShould_work_dates_total();
//            // if employee has get any leaves
//            if (leaves > 0) {
//                salary -= (perDaySalary * leaves);
//                System.out.println("cut for leaves : "+perDaySalary * leaves);
//            }
//            // if employee has worked extra
//            if(extraWorkedDays > 0){
//                salary += (perDaySalary * extraWorkedDays);
//                System.out.println("extra for OT : "+perDaySalary * extraWorkedDays);
//
//            }
//            // deduct ETF
//            salary -= employee.getETF();
//            System.out.println("etf : "+employee.getETF());
//            // deduct advances taken
//            salary -= employee.getAdvance_payments();
//            System.out.println("advance Payment : "+employee.getAdvance_payments());
//            // add bonus
//            salary += employee.getBonus();
//            System.out.println("bonus : "+employee.getBonus());
//            // add 'to be paid' amounts
//            salary -= employee.getLoan_payment_for_month();
//            System.out.println("loan payment"+employee.getLoan_payment_for_month());
//
//            //todo : check this logic ********************************************************************
//            double newLoanToPay = employee.getLoan_to_pay() - employee.getLoan_payment_for_month();
//
////            setLoanToPayInDBbyEmpID(ID, newLoanToPay);
////
////            setCalulatedSalaryInDBbyEmpID(ID, salary);
//            return salary;
//        }
//    }
}
