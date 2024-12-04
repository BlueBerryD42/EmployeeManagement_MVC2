<%-- 
    Document   : EmployeeManagementPage
    Created on : Nov 28, 2024, 7:22:22 AM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="dto.Employee"%>
<%@page import="dto.Department"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employee Management Page</title>
        <link rel="stylesheet" type="text/css" href="style/employee.css">
    </head>

    <body> 
        <c:if test="${requestScope.listEmployee != null}">
            <h1>List of available employee</h1>
            <table id="employeeTable" class="custom-table">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Name</th>
                        <th>BirthDay</th>
                        <th>Mgr No</th>
                        <th>Start Date</th>
                        <th>Salary</th>
                        <th>Status</th>
                        <th>Level</th>
                        <th>Department</th>
                        <th>Skill</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="emp" items="${requestScope.listEmployee}">
                        <tr>
                            <td>${emp.no}</td>
                            <td>${emp.name}</td>
                            <td>${emp.bday}</td>
                            <td>${emp.depNo}</td>
                            <td>${emp.mgrNo}</td>
                            <td>
                                ${java.text.NumberFormat.getNumberInstance().format(emp.salary)}$
                            </td>
                            <td>${emp.status == 1 ? "Active" : "Inactive"}</td>
                            <td>${emp.level}</td>
                            <td>${emp.deptName}</td>
                            <td>${String.join(", ", emp.skills)}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>


            <h1>Create new employee</h1>
            <form action = "" method = "POST">
                <p>Name <input type = "text" name ="name" placeholder="Enter employee name"/></p>
                <p>Birth Day <input type="date" name ="bday"/></p>
                <p>Department 
                    <c:if test="${sessionScope.listDepartments != null}">
                        <select name="deptNo">
                            <c:forEach var="deptName" items="${sessionScope.listDepartments}">
                                <option value="${deptName.id}"> ${deptName.name} </option>
                            </c:forEach>
                        </select>

                    </c:if>
                </p>
                <p>Mgr
                    <select name="mgrNo">
                        <c:forEach var="emp" items="${requestScope.listEmployee}">      
                            <option value="${emp.no}">${emp.name} </option>
                        </c:forEach>
                    </select>
                </p>

                <p>Start Date <input type="date" name ="startDate"/></p>
                <p>Salary <input type ="number" min="0" name="salary"/></p>
                <p>Status 
                    <input type="radio" name ="status" value ="1" checked="">Active
                    <input type="radio" name ="status" value ="0">Inactive
                </p>
                Description<p> <textarea name="note" placeholder="Enter employee description" maxlength="100"></textarea></p>
                <p>Level <input type="number" name="level" min="1"/></p>
                <p><input type="submit" name="action" value="create"/></p>
            </form>
        </c:if>
    </body>
</html>
