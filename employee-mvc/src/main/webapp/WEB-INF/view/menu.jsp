<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav class="navbar navbar-default navbar-static-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <span class="navbar-brand" > </span>
        </div>
        <ul class="nav navbar-nav">
            <li ><a href="/dashboard">Dashboard</a></li> <!-- add class="active" to indicate the active menu -->
            <!-- <li><a href="#">Manage Employee</a></li>
            <li><a href="#">Generate Payroll</a></li>
            <li><a href="#">Generate Payslip</a></li> -->
            <c:forEach items="${menus}" var="menu">
                <li><a href="${menu.menuUrl}">${menu.menuName}</a></li>
            </c:forEach>

        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li>Welcome ${loggedInUser.name} [${loggedInUser.phone}]</li>
            <li><a href="/logout"> Logout</a></li>
        </ul>
    </div>
</nav>
