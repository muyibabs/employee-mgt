package com.muyi.empmgt.mvc.proxy;

import com.muyi.empmgt.mvc.dto.EmployeeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@FeignClient(name = "employee-service")
public interface EmployeeServiceProxy {

    @PostMapping("/employees")
    public EmployeeDto saveEmployee(@RequestBody EmployeeDto empDto);

    @GetMapping("/employees/{id}")
    public EmployeeDto getEmployee(@PathVariable("id") Integer empId);

    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable("id") Integer empId);

    @GetMapping("/employees")
    public Set<EmployeeDto> getAllEmployees();
}
