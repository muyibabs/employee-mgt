package com.muyi.empmgt.payroll.service;

import com.muyi.empmgt.payroll.dto.EmployeeDto;
import com.muyi.empmgt.payroll.dto.PayrollDetailsDto;
import com.muyi.empmgt.payroll.dto.PayslipDto;
import net.sf.jasperreports.engine.JasperPrint;

import java.util.Map;
import java.util.Set;

public interface PayrollService {

    void calculatePayroll(PayrollDetailsDto payrollDetailsDto);

    Set<EmployeeDto> getUnPayrolledEmployees(PayrollDetailsDto payrollDetailsDto);

    PayslipDto generatePaySlip(PayslipDto payslipDto);


}
