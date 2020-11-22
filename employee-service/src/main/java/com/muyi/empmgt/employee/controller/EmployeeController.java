package com.muyi.empmgt.employee.controller;

import com.muyi.empmgt.employee.dto.EmployeeDto;
import com.muyi.empmgt.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/employees")
    public EmployeeDto saveEmployee(@RequestBody EmployeeDto empDto){
        return employeeService.saveEmployee(empDto);
    }

    @GetMapping("/employees/{id}")
    public EmployeeDto getEmployee(@PathVariable("id") Integer empId){
        return employeeService.getEmployeeById(empId);
    }

    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable("id") Integer empId){
        employeeService.deleteEmployeeById(empId);
    }

    @GetMapping("/employees")
    public Set<EmployeeDto> getAllEmployees(){
        return employeeService.getAllEmployee();
    }
}
