package com.jayathilakaRiceProducts.JayathilakaPosBackedWithPrinter.controllers;


import com.jayathilakaRiceProducts.JayathilakaPosBackedWithPrinter.dto.EmployeeDTO;
import com.jayathilakaRiceProducts.JayathilakaPosBackedWithPrinter.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class EmployeeCrud {

    private final EmployeeService employeeService;

    public EmployeeCrud(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * tested
     */
    @PostMapping("/add")
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.createEmployee(employeeDTO);
    }

    /**
     * tested
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable int id) {
        return employeeService.getEmployee(id);
    }

    /**
     * tested
     */
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    /**
     * tested
     * we calculate salary when user details been updated
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable int id, @RequestBody EmployeeDTO employeeDTO) {
        return employeeService.updateEmployee(id, employeeDTO);
    }

    /**
     * tested
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable int id) {
        return employeeService.deleteEmployee(id);
    }


}
