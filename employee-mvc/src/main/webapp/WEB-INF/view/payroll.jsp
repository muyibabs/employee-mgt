<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Payroll</title>
    <link rel="stylesheet" href="css/bootstrap.min.css" integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">
    <link rel="stylesheet" href="css/bootstrap-theme.min.css" integrity="sha384-6pzBo3FDv/PJ8r2KRkGHifhEocL+1X2rVCTTkUfGk7/0pbek5mMa1upzvWbrUbOZ" crossorigin="anonymous">

    <script src="js/bootstrap.min.js" integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd" crossorigin="anonymous"></script>
</head>
<body>
    <div id="header">
        <jsp:include page="menu.jsp"/>
    </div>
    <div class="container">
        <h2>Payroll</h2>
        <form:form  action="/get-pay-employees" method="post" modelAttribute="payrollDetails" >
        <div class="form-row">
            <div class="form-group">
                <label class="control-label col-sm-1" for="year">Year:</label>
                <div class="col-sm-3">
                    <form:select cssClass="form-control" path="year">
                        <form:option value="-1" label="--Please Select--"/>
                        <form:option value="2020" label="2020"/>
                        <form:option value="2019" label="2019"/>
                        <form:option value="2018" label="2018"/>
                    </form:select>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-1" for="month">Month:</label>
                <div class="col-sm-3">
                    <form:select cssClass="form-control" path="month">
                        <form:option value="-1" label="--Please Select--"/>
                        <form:option value="1" label="Jan"/>
                        <form:option value="2" label="Feb"/>
                        <form:option value="3" label="Mar"/>
                        <form:option value="4" label="Apr"/>
                        <form:option value="5" label="May"/>
                        <form:option value="6" label="Jun"/>
                        <form:option value="7" label="Jul"/>
                        <form:option value="8" label="Aug"/>
                        <form:option value="9" label="Sep"/>
                        <form:option value="10" label="Oct"/>
                        <form:option value="11" label="Nov"/>
                        <form:option value="12" label="Dec"/>
                    </form:select>
                </div>
            </div>
            <div class="col-sm-1">
                <button type="submit" class="btn btn-default">Submit</button>
            </div>
        </div>
        </form:form>

        <form:form  action="/do-payroll" method="post" modelAttribute="payrollDetails" >
            <form:hidden  path="year" />
            <form:hidden  path="month" />
            <!-- Table here -->
            <table class="table table-striped table-bordered">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">First Name</th>
                    <th scope="col">Last Name</th>
                    <th scope="col">Sex</th>
                    <th scope="col">Designation</th>
                    <th scope="col">Department</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="employee" items="${employees}" varStatus="loop">
                <tr>
                    <td>
                        <form:checkbox path="employeeIds" value="${employee.employeeId}"/>
                    </td>
                    <td>${employee.firstName}</td>
                    <td>${employee.lastName}</td>
                    <td>${employee.sex}</td>
                    <td>${employee.designation}</td>
                    <td>${employee.department}</td>
                </tr>
                </c:forEach>
                </tbody>
            </table>
            <button type="submit" class="btn btn-default">Generate Payroll</button>
        </form:form>
    </div>
</body>
</html>