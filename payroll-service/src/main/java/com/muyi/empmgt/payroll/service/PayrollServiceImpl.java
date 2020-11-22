package com.muyi.empmgt.payroll.service;

import com.muyi.empmgt.payroll.dto.EmployeeDto;
import com.muyi.empmgt.payroll.dto.PayrollDetailsDto;
import com.muyi.empmgt.payroll.dto.PayslipDto;
import com.muyi.empmgt.payroll.model.PayItem;
import com.muyi.empmgt.payroll.model.Payroll;
import com.muyi.empmgt.payroll.proxy.EmployeeServiceProxy;
import com.muyi.empmgt.payroll.repository.PayItemRepository;
import com.muyi.empmgt.payroll.repository.PayrollRepository;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PayrollServiceImpl implements PayrollService {

    @Autowired
    private PayrollRepository payrollRepository;
    @Autowired
    private PayItemRepository payItemRepository;
    @Autowired
    private EmployeeServiceProxy employeeServiceProxy;

    @Override
    public void calculatePayroll(PayrollDetailsDto payrollDetailsDto) {
        for(Integer empId: payrollDetailsDto.getEmployeeIds()) {
            //fetch employee...skip if not found

            EmployeeDto employeeDto = employeeServiceProxy.getEmployeeDtoById(empId);
            if (employeeDto == null) {
                continue;
            }

            //check if payroll is already calculated skip if so
            Payroll payroll = payrollRepository.findByEmployeeIdAndYearAndMonth(empId, payrollDetailsDto.getYear(), payrollDetailsDto.getMonth());
            if (payroll != null) {
                continue;
            }

            //calculate payroll
            //create payroll entry, and payitems entries
            payroll = new Payroll();
            payroll.setEmployeeId(employeeDto.getEmployeeId());
            payroll.setYear(payrollDetailsDto.getYear());
            payroll.setMonth(payrollDetailsDto.getMonth());

            PayItem basicSalaryPayitem = createPayItem("Basic Salary", "plus", employeeDto.getBasicSalary(), payroll);
            PayItem hraPayitem = createPayItem("HRA", "plus", employeeDto.getHra(), payroll);
            PayItem conveyancePayitem = createPayItem("Conveyance Allowance", "plus", employeeDto.getConveyanceAllowance(), payroll);
            PayItem medicalPayitem = createPayItem("Medical Allowance", "plus", employeeDto.getMedicalAllowance(), payroll);
            PayItem ltaPayitem = createPayItem("LTA", "plus", employeeDto.getLta(), payroll);
            PayItem specialPayitem = createPayItem("Special Allowance", "plus", employeeDto.getSpecialAllowance(), payroll);
            PayItem incomeTaxPayitem = createPayItem("Income Tax", "minus", employeeDto.getIncomeTax(), payroll);

            payrollRepository.save(payroll);
        }
    }

    @Override
    public Set<EmployeeDto> getUnPayrolledEmployees(PayrollDetailsDto payrollDetailsDto) {
        Set<EmployeeDto> employeeDtos = employeeServiceProxy.getAllEmployees();
        List<Payroll> payrollList = payrollRepository.findByYearAndMonth(payrollDetailsDto.getYear(), payrollDetailsDto.getMonth());
        List<Integer> payrollEmpIds = payrollList.stream().map(payroll -> payroll.getEmployeeId()).collect(Collectors.toList());
        List<EmployeeDto> unPayrolledEmployeeDtos = employeeDtos.stream()
                .filter(employeeDto -> !payrollEmpIds.contains(employeeDto.getEmployeeId()))
                .collect(Collectors.toList());
        return new HashSet<EmployeeDto>(unPayrolledEmployeeDtos);
    }

    @Override
    public PayslipDto generatePaySlip(PayslipDto payslipDto) {
        Map<String, Object> parameterMap = new HashMap<>();
//        try {
//            File file = ResourceUtils.getFile("classpath:Payslip.jrxml");
//            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
//            //JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource();
//            JRDataSource jrDataSource = new JREmptyDataSource();
            //Fetch EmployeeDto and PayrollDto
            EmployeeDto employeeDto = employeeServiceProxy.getEmployeeDtoById(payslipDto.getEmployeeId());
            Payroll payroll = payrollRepository.findByEmployeeIdAndYearAndMonth(payslipDto.getEmployeeId(), payslipDto.getYear(), payslipDto.getMonth());

        payslipDto = getParameterPayslip(employeeDto, payroll);
        //...
            //jasperPrint = JasperFillManager.fillReport(jasperReport, parameterMap, jrDataSource);
            //First save to file, then see how to return to user
            //JasperExportManager.exportReportToPdfFile(jasperPrint, "my_payslip.pdf");
//        } catch (FileNotFoundException | JRException e) {
//            e.printStackTrace();
//        }
        return payslipDto;
    }

    private PayItem createPayItem(String name, String type, BigDecimal amount, Payroll payroll){
        PayItem payItem = new PayItem();
        payItem.setName(name);
        payItem.setType(type);
        payItem.setAmount(amount);
        payItem.setPayroll(payroll);
        payroll.addPayItem(payItem);
        return payItem;
    }

    private PayslipDto getParameterPayslip(EmployeeDto empDto, Payroll payroll){
        PayslipDto payslipDto = new PayslipDto();
        payslipDto.setMonth(payroll.getMonth());
        payslipDto.setYear(payroll.getYear());
        payslipDto.setEmployeeId(empDto.getEmployeeId());
        payslipDto.setEmpName(empDto.getFirstName()+" " + empDto.getLastName());
        payslipDto.setDesignation( empDto.getDesignation());
        payslipDto.setHireDate( empDto.getHireDate());
        payslipDto.setWorkLocation( empDto.getWorkLocation());
        payslipDto.setSex( empDto.getSex());
        payslipDto.setAddress( empDto.getAddress());
        payslipDto.setDepartment( empDto.getDepartment());
        payslipDto.setPfNo( empDto.getPfNo());
        //Get Payitems and get each one out
        payslipDto.setBasicSalary( getPayItemByName(payroll.getPayitems(), "Basic Salary").getAmount());
        payslipDto.setHra( getPayItemByName(payroll.getPayitems(), "HRA").getAmount());
        payslipDto.setConveyanceAllowance( getPayItemByName(payroll.getPayitems(), "Conveyance Allowance").getAmount());
        payslipDto.setMedicalAllowance( getPayItemByName(payroll.getPayitems(), "Medical Allowance").getAmount());
        payslipDto.setLta( getPayItemByName(payroll.getPayitems(), "LTA").getAmount());
        payslipDto.setSpecialAllowance( getPayItemByName(payroll.getPayitems(), "Special Allowance").getAmount());
        payslipDto.setIncomeTax( getPayItemByName(payroll.getPayitems(), "Income Tax").getAmount());

        return payslipDto;
    }

    private PayItem getPayItemByName(Set<PayItem> payItems, String itemName){
        for(PayItem payItem: payItems){
            if(payItem.getName().equals(itemName))
                return payItem;
        }
        return null;
    }
}
