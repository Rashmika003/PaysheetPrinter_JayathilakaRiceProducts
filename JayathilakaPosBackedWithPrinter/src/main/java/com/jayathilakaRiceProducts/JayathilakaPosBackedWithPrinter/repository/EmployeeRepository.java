package com.jayathilakaRiceProducts.JayathilakaPosBackedWithPrinter.repository;

import com.jayathilakaRiceProducts.JayathilakaPosBackedWithPrinter.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;



public interface EmployeeRepository extends JpaRepository<Employee, Integer> {


}
