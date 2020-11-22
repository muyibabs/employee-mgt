package com.muyi.empmgt.mvc.controller;

import com.muyi.empmgt.mvc.dto.*;
import com.muyi.empmgt.mvc.proxy.EmployeeServiceProxy;
import com.muyi.empmgt.mvc.proxy.PayrollServiceProxy;
import com.muyi.empmgt.mvc.proxy.UserServiceProxy;
import net.sf.jasperreports.engine.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@SessionAttributes({"menus", "loggedInUser"})

public class MainController {

    @Autowired
    UserServiceProxy userServiceProxy;
    @Autowired
    EmployeeServiceProxy employeeServiceProxy;
    @Autowired
    PayrollServiceProxy payrollServiceProxy;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }


    @GetMapping("/")
    public String goHome(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "index";
    }

    @PostMapping("/login")
    public String authenticateUser(@ModelAttribute UserDto userDto, Model model) {

        userDto = userServiceProxy.validateUser(userDto);
        if (userDto == null || userDto.getUserId() == null) {
            model.addAttribute("user", new UserDto());
            model.addAttribute("errorMessage", "Invalid Username/Password");
            return "index";
        }
        //fetch menus
        Set<MenuDto> menus = userServiceProxy.getMenusByUserId(userDto.getUserId());
        model.addAttribute("menus", menus);
        model.addAttribute("loggedInUser", userDto);

        return "dashboard";
    }

    @GetMapping("/dashboard")
    public String getDashboard() {
        return "dashboard";
    }

    @GetMapping("/employees")
    public String getEmployeesPage(Model model) {
        //fetch all employees
        Set<EmployeeDto> allEmployees = employeeServiceProxy.getAllEmployees();
        model.addAttribute("employees", allEmployees);
        return "employees";
    }

    @GetMapping("/new-employee")
    public String showNewEmployeePage(Model model) {
        EmployeeDto employeeDto = new EmployeeDto();
        model.addAttribute("employee", employeeDto);
        return "emp-add";
    }

    @PostMapping("/save-employee")
    public String saveEmployee(@ModelAttribute EmployeeDto employeeDto) {
        employeeServiceProxy.saveEmployee(employeeDto);
        return "redirect:/employees";
    }

    @GetMapping("/delete-employee/{empId}")
    public String deleteEmployee(@PathVariable Integer empId) {
        employeeServiceProxy.deleteEmployee(empId);
        return "redirect:/employees";
    }

    @GetMapping("/edit-employee/{empId}")
    public String editEmployee(@PathVariable Integer empId, Model model) {
        EmployeeDto employeeDto = employeeServiceProxy.getEmployee(empId);
        if (employeeDto == null)
            return "redirect:/employees";

        model.addAttribute("employee", employeeDto);
        return "emp-add";
    }

    @GetMapping("/logout")
    public String logout(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "redirect:/";
    }

    @GetMapping("/payroll")
    public String getPayrollPage(Model model) {
        PayrollDetailsDto payrollDetailsDto = new PayrollDetailsDto();
        model.addAttribute("payrollDetails", payrollDetailsDto);
        return "payroll";
    }

    @PostMapping("/get-pay-employees")
    public String getUnPayrolledEmployees(@ModelAttribute PayrollDetailsDto payrollDetailsDto, Model model) {
        setPayrollModelDetails(payrollDetailsDto, model);
        return "payroll";
    }

    @PostMapping("/do-payroll")
    public String doPayroll(@ModelAttribute PayrollDetailsDto payrollDetailsDto, Model model) {
        //Call service doPayroll method
        payrollServiceProxy.calculate(payrollDetailsDto);
        //--------------
        setPayrollModelDetails(payrollDetailsDto, model);
        return "payroll";
    }

    @GetMapping("/payslip")
    public String getPayslipPage(Model model) {
        PayslipDto payslipDto = new PayslipDto();
        model.addAttribute("payslip", payslipDto);
        return "payslip";
    }

    @PostMapping("/get-payslip")
    public String getPayslip(@ModelAttribute PayslipDto payslipDto, Model model, HttpServletResponse response) {
        Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.info("PayslipDto: empId=" + payslipDto.getEmployeeId() + "   year=" + payslipDto.getYear() +
                "    month=" + payslipDto.getMonth());
        try {
            //Call service doPayroll method
            File file = ResourceUtils.getFile("classpath:Payslip.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
            JRDataSource jrDataSource = new JREmptyDataSource();

            PayslipDto fetchedPayslipDto = payrollServiceProxy.fetchSlipParams(payslipDto);
            Map<String, Object> parameterMap = getParameterMap(fetchedPayslipDto);
            //...
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameterMap, jrDataSource);
            //First save to file, then see how to return to user
            //JasperExportManager.exportReportToPdfFile(jasperPrint, "my_payslip.pdf");
            //================
            response.setContentType("application/x-download");
            response.setHeader("Content-Disposition", String.format("attachment; filename=\"payslip.pdf\""));
            logger.info("====================================");
            OutputStream out = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, out);
            //--------------
            model.addAttribute("payslip", payslipDto);
            out.flush();
            out.close();
        } catch (IOException | JRException e) {
            e.printStackTrace();
        }
        return "payslip";
    }

    private void setPayrollModelDetails(@ModelAttribute PayrollDetailsDto payrollDetailsDto, Model model) {
        Set<EmployeeDto> unPayrolledEmployees = payrollServiceProxy.getUnPayrolledEmployees(payrollDetailsDto);
        model.addAttribute("employees", unPayrolledEmployees);
        model.addAttribute("payrollDetails", payrollDetailsDto);
    }

    private Map<String, Object> getParameterMap(PayslipDto payslipDto){
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("month", payslipDto.getMonth());
        parameters.put("payPeriod", months[payslipDto.getMonth()-1]+"-" + payslipDto.getYear());
        parameters.put("empName", payslipDto.getEmpName());
        parameters.put("designation", payslipDto.getDesignation());
        parameters.put("hireDate", payslipDto.getHireDate());
        parameters.put("workLocation", payslipDto.getWorkLocation());
        parameters.put("sex", payslipDto.getSex());
        parameters.put("address", payslipDto.getAddress());
        parameters.put("department", payslipDto.getDepartment());
        parameters.put("pfNo", payslipDto.getPfNo());
        //Get Payitems and get each one out
        parameters.put("basicSalary", payslipDto.getBasicSalary());
        parameters.put("hra", payslipDto.getHra());
        parameters.put("conveyanceAllowance", payslipDto.getConveyanceAllowance());
        parameters.put("medicalAllowance", payslipDto.getMedicalAllowance());
        parameters.put("lta", payslipDto.getLta());
        parameters.put("specialAllowance", payslipDto.getSpecialAllowance());
        parameters.put("incomeTax", payslipDto.getIncomeTax());

        return parameters;
    }

//    @RequestMapping(value = "/export", method = RequestMethod.POST)
//    public void export(ModelAndView model, HttpServletResponse response) throws IOException, JRException, SQLException {
//        JasperPrint jasperPrint = null;
//
//        response.setContentType("application/x-download");
//        response.setHeader("Content-Disposition", String.format("attachment; filename=\"users.pdf\""));
//
//        OutputStream out = response.getOutputStream();
//        jasperPrint = userService.exportPdfFile();
//        JasperExportManager.exportReportToPdfStream(jasperPrint, out);
//    ===========
//            response.setContentType("application/x-pdf");
//    response.setHeader("Content-disposition", "inline; filename=helloWorldReport.pdf");
//
//    final OutputStream outStream = response.getOutputStream();
//    JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
//    }

//    public static void main(String[] args) {
//        UserDto u1 = new UserDto(); u1.setUserId(1); u1.setName("Angel");
//        UserDto u2 = new UserDto(); u2.setUserId(2); u2.setName("Eva");
//        ArrayList<UserDto> list = null;
//        list.add(u1); list.add(u2);
//        List<String> names = list.stream().map(userDto -> userDto.getName()).collect(Collectors.toList());
//        System.out.println(names);
//    }
}
