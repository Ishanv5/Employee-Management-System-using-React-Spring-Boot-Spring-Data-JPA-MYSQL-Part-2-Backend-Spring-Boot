package com.ishantech.employee.service;

import com.ishantech.employee.entity.Employee;
import com.ishantech.employee.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {


    private  EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public Employee postEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    public  void deleteEmployees(long id){
        if(!employeeRepository.existsById(id)){
            throw new EntityNotFoundException("Employee with id "+id+" does not exist");
        }
            employeeRepository.deleteById(id);
    }

    public Employee getEmployeeById(long id){
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee updateEmployee(Long id, Employee employee){
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if(employeeOptional.isPresent()) {
            Employee employee1 = employeeOptional.get();
            employee1.setName(employee.getName());
            employee1.setEmail(employee.getEmail());
            employee1.setPhone(employee.getPhone());
            employee1.setDepartment(employee.getDepartment());
            return employeeRepository.save(employee1);
        }
        return null;
    }
}
