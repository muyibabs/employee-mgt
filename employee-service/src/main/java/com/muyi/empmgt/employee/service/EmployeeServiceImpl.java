package com.muyi.empmgt.employee.service;

import com.muyi.empmgt.employee.dto.EmployeeDto;
import com.muyi.empmgt.employee.model.Employee;
import com.muyi.empmgt.employee.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDto, employee);
        employee = employeeRepository.save(employee);
        BeanUtils.copyProperties(employee, employeeDto);
        return employeeDto;
    }

    @Override
    public EmployeeDto getEmployeeById(Integer empId) {
        Optional<Employee> optional = employeeRepository.findById(empId);
        if(!optional.isPresent())
            return null;
        EmployeeDto employeeDto = new EmployeeDto();
        BeanUtils.copyProperties(optional.get(), employeeDto);
        return employeeDto;
    }

    @Override
    public void deleteEmployeeById(Integer empId) {
        employeeRepository.deleteById(empId);
    }

    @Override
    public Set<EmployeeDto> getAllEmployee() {
        Set<EmployeeDto> employees = new HashSet<>();
        List<Employee> all = employeeRepository.findAll();
        if(all==null || all.size()==0)
            return employees;

        for(Employee emp: all){
            EmployeeDto dto = new EmployeeDto();
            BeanUtils.copyProperties(emp, dto);
            employees.add(dto);
        }
        return employees;
    }
}
