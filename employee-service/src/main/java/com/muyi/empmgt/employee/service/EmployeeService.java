package com.muyi.empmgt.employee.service;

import com.muyi.empmgt.employee.dto.EmployeeDto;

import java.util.Set;

public interface EmployeeService {

    EmployeeDto saveEmployee(EmployeeDto employeeDto);
    EmployeeDto getEmployeeById(Integer empId);
    void deleteEmployeeById(Integer empId);
    Set<EmployeeDto> getAllEmployee();

}
