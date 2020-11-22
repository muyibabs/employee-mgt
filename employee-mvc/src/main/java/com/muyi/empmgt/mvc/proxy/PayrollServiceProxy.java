package com.muyi.empmgt.mvc.proxy;

import com.muyi.empmgt.mvc.dto.EmployeeDto;
import com.muyi.empmgt.mvc.dto.PayrollDetailsDto;
import com.muyi.empmgt.mvc.dto.PayslipDto;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;
import java.util.Set;

@FeignClient(name = "payroll-service")
public interface PayrollServiceProxy {

    @PostMapping("/payrolls")
    public void calculate(@RequestBody PayrollDetailsDto payrollDetailsDto);

    @PostMapping("/no-payroll-employees")
    public Set<EmployeeDto> getUnPayrolledEmployees(@RequestBody PayrollDetailsDto payrollDetailsDto);

//    @PostMapping("/get-slip")
//    public JasperPrint doMySlip(@RequestBody PayslipDto payslipDto);

    @PostMapping("/fetch-slip-params")
    public PayslipDto fetchSlipParams(@RequestBody PayslipDto payslipDto);

}
