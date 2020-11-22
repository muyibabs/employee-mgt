<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Employees</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css" integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">
    <link rel="stylesheet" href="../css/bootstrap-theme.min.css" integrity="sha384-6pzBo3FDv/PJ8r2KRkGHifhEocL+1X2rVCTTkUfGk7/0pbek5mMa1upzvWbrUbOZ" crossorigin="anonymous">

    <script src="../js/bootstrap.min.js" integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd" crossorigin="anonymous"></script>
    <script>
        function deleteConfirm(empId) {
          if (confirm("Are you sure!")) {
            window.location.href = '/delete-employee/'+empId;
          }
    }
    </script>
</head>
<body>

<div id="header">
    <jsp:include page="menu.jsp"/>
</div>

<h2>Employee List</h2>
<div class="container">
    <p>
        <a class="btn btn-info" href="/new-employee">New Employee</a>
    </p>
</div>
<div class="container">
    <table class="table table-striped table-bordered">
        <thead>
        <tr>
            <th scope="col">First Name</th>
            <th scope="col">Last Name</th>
            <th scope="col">Sex</th>
            <th scope="col">Designation</th>
            <th scope="col">Department</th>
            <th scope="col">Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="employee" items="${employees}" varStatus="loop">
        <tr>
            <td>${employee.firstName}</td>
            <td>${employee.lastName}</td>
            <td>${employee.sex}</td>
            <td>${employee.designation}</td>
            <td>${employee.department}</td>
            <td>
                <a class="btn btn-info" href="/edit-employee/${employee.employeeId}">Edit</a>
                <a class="btn btn-info" href="javascript:deleteConfirm(${employee.employeeId});">Delete</a>
            </td>
        </tr>
        </c:forEach>

        </tbody>
    </table>
</div>
</body>
</html>