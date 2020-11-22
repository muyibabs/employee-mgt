<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/bootstrap.min.css" integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">
    <link rel="stylesheet" href="css/bootstrap-theme.min.css" integrity="sha384-6pzBo3FDv/PJ8r2KRkGHifhEocL+1X2rVCTTkUfGk7/0pbek5mMa1upzvWbrUbOZ" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <script src="js/bootstrap.min.js" integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd" crossorigin="anonymous"></script>
    <script src='js/jquery.min.js'></script>
    <script src='js/bootstrapvalidator.min.js'></script>
    <script src="js/validate-employee.js"></script>
</head>
<body>
<div id="header">
    <jsp:include page="menu.jsp"/>
</div>
<c:set value="New" var="opType"/>
    <c:if test = "${not empty employee.employeeId}">
        <c:set var="opType" value="Edit" />
    </c:if>
<div class="container">
    <h2>${opType} Employee</h2>
</div>
<form:form  cssClass="form-horizontal" action="/save-employee" method="post" modelAttribute="employee" id="employeeForm">
    <form:hidden  path="employeeId" />
    <div class="row">
        <div class="col-md-offset-1 col-md-4">

            <div class="form-group">
                <label class="control-label" for="fname">First Name:</label>
                <form:input path="firstName" cssClass="form-control" id="fname" placeholder="First Name" />
            </div>
            <div class="form-group">
                <label class="control-label " for="mname">Middle Name:</label>
                <form:input path="middleName" cssClass="form-control" id="mname" placeholder="Middle Name" />
            </div>
            <div class="form-group">
                <label class="control-label " for="lname">Last Name:</label>
                <form:input path="lastName" cssClass="form-control" id="lname" placeholder="Last Name" />
            </div>
            <div class="form-group">
                <label class="control-label " >Sex:</label>
                <div class="radio">
                    <label ><form:radiobutton path="sex" value="male" />Male</label>
                </div>
                <div class="radio">
                    <label ><form:radiobutton path="sex" value="female" />Female</label>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label " for="birthDate">Date of Birth:</label>
                <form:input path="birthDate" cssClass="form-control" id="birthDate" placeholder="mm/dd/yyyy" />
            </div>
            <div class="form-group">
                <label class="control-label " for="address">Address:</label>
                <form:input path="address" cssClass="form-control" id="address" placeholder="Address" />
            </div>
            <div class="form-group">
                <label class="control-label " for="city">City:</label>
                <form:input path="city" cssClass="form-control" id="city" placeholder="City" />
            </div>
            <div class="form-group">
                <label class="control-label " for="state">State:</label>
                <form:input path="state" cssClass="form-control" id="state" placeholder="State" />
            </div>
            <div class="form-group">
                <label class="control-label " for="hra">HRA:</label>
                <form:input path="hra" cssClass="form-control" id="hra" placeholder="HRA" />
            </div>
            <div class="form-group">
                <label class="control-label " for="lta">LTA:</label>
                <form:input path="lta" cssClass="form-control" id="lta" placeholder="LTA" />
            </div>
            <div class="form-group">
                <label class="control-label " for="workLocation">Work Location:</label>
                <form:input path="workLocation" cssClass="form-control" id="workLocation" placeholder="Work Location" />
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-success" >Submit</button>
            </div>
        </div>
        <div class="col-md-offset-1 col-md-4">
            <div class="form-group">
                <label class="control-label " for="designation">Designation:</label>
                <form:input path="designation" cssClass="form-control" id="designation" placeholder="Designation" />
            </div>
            <div class="form-group">
                <label class="control-label " for="department">Department:</label>
                <form:input path="department" cssClass="form-control" id="department" placeholder="Department" />
            </div>
            <div class="form-group">
                <label class="control-label " for="hireDate">Date of Hire:</label>
                <form:input path="hireDate" cssClass="form-control" id="hireDate" placeholder="mm/dd/yyyy" />
            </div>
            <div class="form-group">
                <label class="control-label " for="pfno">PF No:</label>
                <form:input path="pfNo" cssClass="form-control" id="pfno" placeholder="PF No" />
            </div>

            <div class="form-group">
                <label class="control-label " for="bankAcctNo">Bank Acct No:</label>
                <form:input path="bankAcctNo" cssClass="form-control" id="bankAcctNo" placeholder="Bank Acct No" />
            </div>
            <div class="form-group">
                <label class="control-label " for="basicSalary">Basic Salary:</label>
                <form:input path="basicSalary" cssClass="form-control" id="basicSalary" placeholder="Basic Salary" />
            </div>

            <div class="form-group">
                <label class="control-label " for="conveyanceAllowance">Conveyance Allowance:</label>
                <form:input path="conveyanceAllowance" cssClass="form-control" id="conveyanceAllowance" placeholder="Conveyance Allowance" />
            </div>
            <div class="form-group">
                <label class="control-label " for="medicalAllowance">Medical Allowance:</label>
                <form:input path="medicalAllowance" cssClass="form-control" id="medicalAllowance" placeholder="Medical Allowance" />
            </div>

            <div class="form-group">
                <label class="control-label " for="specialAllowance">Special Allowance:</label>
                <form:input path="specialAllowance" cssClass="form-control" id="specialAllowance" placeholder="Special Allowance" />
            </div>
            <div class="form-group">
                <label class="control-label " for="incomeTax">Income Tax:</label>
                <form:input path="incomeTax" cssClass="form-control" id="incomeTax" placeholder="Income Tax" />
            </div>

        </div>
    </div>

</form:form>
</body>
</html>