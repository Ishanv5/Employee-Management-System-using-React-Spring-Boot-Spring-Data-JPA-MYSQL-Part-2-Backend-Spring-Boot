package com.ishantech.employee.controller;

import com.ishantech.employee.entity.Employee;
import com.ishantech.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin("*")
public class EmployeeController {


    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/postEmployee")
    public Employee postEmployee(@RequestBody Employee employee) {
        return employeeService.postEmployee(employee);
    }

    @GetMapping("/employee")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable long id) {
        try {
            employeeService.deleteEmployees(id);
            return new ResponseEntity<>("Employee with ID " + id + " deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable long id) {

        Employee employee = employeeService.getEmployeeById(id);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employee);

    }

    @PatchMapping("/employee/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable long id, @RequestBody Employee employee) {

        Employee updatedEmployee = employeeService.updateEmployee(id, employee);
        if (updatedEmployee == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(updatedEmployee);


    }
}
