package com.muyi.empmgt.payroll.proxy;

import com.muyi.empmgt.payroll.dto.EmployeeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@FeignClient(name = "employee-service")
public interface EmployeeServiceProxy {

    @GetMapping("/employees/{id}")
    EmployeeDto getEmployeeDtoById(@PathVariable Integer id);

    @PostMapping("/employees")
    EmployeeDto saveEmployee(@RequestBody EmployeeDto empDto);

//    @GetMapping("/employees/{id}")
//    public EmployeeDto getEmployee(@PathVariable("id") Integer empId);

    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable("id") Integer empId);

    @GetMapping("/employees")
    Set<EmployeeDto> getAllEmployees();
}
