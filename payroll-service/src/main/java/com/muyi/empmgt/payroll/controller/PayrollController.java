package com.muyi.empmgt.payroll.controller;

import com.muyi.empmgt.payroll.dto.EmployeeDto;
import com.muyi.empmgt.payroll.dto.PayrollDetailsDto;
import com.muyi.empmgt.payroll.dto.PayslipDto;
import com.muyi.empmgt.payroll.service.PayrollService;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

@RestController
public class PayrollController {

    @Autowired
    private PayrollService payrollService;

    @PostMapping("/payrolls")
    public void calculate(@RequestBody PayrollDetailsDto payrollDetailsDto){
        payrollService.calculatePayroll(payrollDetailsDto);
    }

    @PostMapping("/no-payroll-employees")
    public Set<EmployeeDto> getUnPayrolledEmployees(@RequestBody PayrollDetailsDto payrollDetailsDto){
        return payrollService.getUnPayrolledEmployees(payrollDetailsDto);
    }

    @PostMapping("/fetch-slip-params")
    public PayslipDto fetchSlipParams(@RequestBody PayslipDto payslipDto){
        return payrollService.generatePaySlip(payslipDto);
    }
}
