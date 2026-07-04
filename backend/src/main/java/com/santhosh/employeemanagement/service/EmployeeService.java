package com.santhosh.employeemanagement.service;

import com.santhosh.employeemanagement.entity.Employee;
import com.santhosh.employeemanagement.exception.EmployeeNotFoundException;
import com.santhosh.employeemanagement.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {

        logger.info("Fetching all employees");

        return employeeRepository.findAll();
    }

    public Employee saveEmployee(Employee employee) {

        logger.info("Creating employee: {}", employee.getName());

        Employee savedEmployee = employeeRepository.save(employee);

        logger.info("Employee created successfully with ID: {}", savedEmployee.getId());

        return savedEmployee;
    }

    public Employee getEmployeeById(Long id) {

        logger.info("Fetching employee with ID: {}", id);

        return employeeRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Employee not found with ID: {}", id);
                    return new EmployeeNotFoundException("Employee not found with ID : " + id);
                });
    }

    public Employee updateEmployee(Long id, Employee updatedEmployee) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        employee.setName(updatedEmployee.getName());
        employee.setEmail(updatedEmployee.getEmail());
        employee.setDepartment(updatedEmployee.getDepartment());
        employee.setSalary(updatedEmployee.getSalary());

        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with ID : " + id));

        employeeRepository.delete(employee);
    }

}