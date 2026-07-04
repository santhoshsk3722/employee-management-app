package com.santhosh.employeemanagement.controller;

import com.santhosh.employeemanagement.entity.Employee;
import com.santhosh.employeemanagement.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import jakarta.validation.Valid;

import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @PostMapping("/employees")
    public Employee createEmployee(@Valid @RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @PutMapping("/employees/{id}")
    public Employee updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody Employee employee) {

        return employeeService.updateEmployee(id, employee);
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable Long id) {

        employeeService.deleteEmployee(id);

        return "Employee deleted successfully.";
    }
}