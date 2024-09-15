package com.jayathilakaRiceProducts.JayathilakaPosBackedWithPrinter.service;

import com.jayathilakaRiceProducts.JayathilakaPosBackedWithPrinter.dto.EmployeeDTO;
import com.jayathilakaRiceProducts.JayathilakaPosBackedWithPrinter.model.Employee;
import com.jayathilakaRiceProducts.JayathilakaPosBackedWithPrinter.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public ResponseEntity<?> createEmployee(EmployeeDTO employeeDTO) {
        try {
            Employee employee = new Employee();
            employee.setName(employeeDTO.getName());
            employee.setPosition(employeeDTO.getPosition());
            employee.setMonthly_Salary(employeeDTO.getMonthly_Salary());
            employee.setETF(employeeDTO.getETF());
            employee.setBonus(employeeDTO.getBonus());
            employee.setAdvance_payments(employeeDTO.getAdvance_payments());
            employee.setLoan_to_pay(employeeDTO.getLoan_to_pay());
            employee.setLoan_payment_for_month(employeeDTO.getLoan_payment_for_month());
            employee.setWorked_days_count(employeeDTO.getWorked_days_count());
            employee.setShould_work_dates_total(employeeDTO.getShould_work_dates_total());

            employeeRepository.save(employee);
            return ResponseEntity.ok("employee successfully added to Database");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating employee: " + e.getMessage());
        }
    }

    public ResponseEntity<?> getEmployee(int id) {
        try {
            Optional<Employee> employeeOpt = employeeRepository.findById(id);
            if (employeeOpt.isPresent()) {
                Employee employee = employeeOpt.get();
                return ResponseEntity.ok(employee);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found with id: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching employee: " + e.getMessage());
        }
    }

    public ResponseEntity<?> getAllEmployees() {
        try {
            List<Employee> employees = employeeRepository.findAll();
            return ResponseEntity.ok(employees);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching employees: " + e.getMessage());
        }
    }

    @Transactional
    public ResponseEntity<?> updateEmployee(int id, EmployeeDTO employeeDTO) {
        try {
            Optional<Employee> employeeOpt = employeeRepository.findById(id);
            if (employeeOpt.isPresent()) {
                Employee employee = employeeOpt.get();
                employee.setName(employeeDTO.getName());
                employee.setPosition(employeeDTO.getPosition());
                employee.setMonthly_Salary(employeeDTO.getMonthly_Salary());
                employee.setETF(employeeDTO.getETF());
                employee.setBonus(employeeDTO.getBonus());
                employee.setAdvance_payments(employeeDTO.getAdvance_payments());
                employee.setLoan_to_pay(employeeDTO.getLoan_to_pay());
                employee.setLoan_payment_for_month(employeeDTO.getLoan_payment_for_month());
                employee.setWorked_days_count(employeeDTO.getWorked_days_count());
                employee.setShould_work_dates_total(employeeDTO.getShould_work_dates_total());

                employee = employeeRepository.save(employee);

                log.info("[EmployeeService : updateEmployee] Updated Successfully : id {}", id);
                return ResponseEntity.ok("Updated Successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found with id: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating employee: " + e.getMessage());
        }
    }

    @Transactional
    public ResponseEntity<?> deleteEmployee(int id) {
        try {
            if (employeeRepository.existsById(id)) {
                employeeRepository.deleteById(id);
                return ResponseEntity.ok("Employee deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found with id: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting employee: " + e.getMessage());
        }
    }


}


