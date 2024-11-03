package com.jayathilakaRiceProducts.JayathilakaPosBackedWithPrinter.repository;

import com.jayathilakaRiceProducts.JayathilakaPosBackedWithPrinter.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE Employee e SET e.month = :newMonth")
    void updateAllEmployeeMonths(@Param("newMonth") int newMonth);

    @Modifying
    @Transactional
    @Query("UPDATE Employee e SET e.should_work_dates_total = :count")
    void updateAllEmployeeShouldWorkDates(@Param("count") int count);

    @Modifying
    @Transactional
    @Query("UPDATE Employee e SET e.month = :newMonth, e.should_work_dates_total = :count")
    void updateAllEmployeeMonthAndWorkDates(@Param("newMonth") int newMonth, @Param("count") int count);

}
