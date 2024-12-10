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

        <!--        List of employee-->
        <h1>List of available employee</h1>
        <form action="MainController" method="POST">
            Search Employee:<input type="text" name="search" value="${param.search}"/>
            <input type="hidden" name="action" value="ViewEmployee"/>
            <input type="submit" name="action" value="Search"/>
        </form>
        </br>
        <c:if test="${requestScope.listEmployee != null}">
            <table id="employeeTable" class="custom-table">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Name</th>
                        <th>BirthDay</th>
                        <th>Department No</th>
                        <th>Mgr No</th>
                        <th>Start Date</th>
                        <th>Salary</th>
                        <th>Status</th>
                        <th>Level</th>
                        <th>Department</th>
                        <th>Skill</th>
                        <th>Update Status</th>
                    </tr>
                </thead>
                <tbody>

                    <c:forEach var="emp" items="${requestScope.listEmployee}">
                    <form action="MainController" method="POST">
                        <tr>
                        <input type="hidden" name ="no" value= "${emp.no}"/>
                        <td>${emp.no}</td>
                        <td>${emp.name}</td>
                        <td>${emp.bday}</td>
                        <td>${emp.depNo}</td>
                        <td>${emp.mgrNo}</td>
                        <td>${emp.startDate}</td>
                        <td>${emp.salary}$</td>
                        <input type="hidden" name="status" value="${emp.status}"/>
                        <td>${emp.status == 1 ? "Active" : "Inactive"}</td>
                        <td>${emp.level}</td>
                        <td>${emp.deptName}</td>
                        <td>${String.join(", ", emp.skills)}</td>
                        <input type="hidden" name="action" value="UPDATE_EMPLOYEE_STATUS"/>
                        <td><input type ="submit" value="UPDATE" name="action"/></td>
                        </tr>
                    </form>
                </c:forEach>
            </tbody>
        </table>

        <!--            Form for creating new employee-->
        <h1>Create new employee</h1>
        <form action = "MainController" method = "POST">
            <p>Name <input type = "text" name ="name" placeholder="Enter employee name" required=""/></p>
            <p>Birth Day <input type="date" name ="bday" required=""/></p>
            <p>Department 
                <c:if test="${sessionScope.listDepartments != null}">
                    <select name="deptNo" required="">
                        <c:forEach var="deptName" items="${sessionScope.listDepartments}">
                            <option value="${deptName.id}"> ${deptName.name} </option>
                        </c:forEach>
                    </select>

                </c:if>
            </p>
            <p>Manager
                <select name="mgrNo" required="">
                    <c:forEach var="emp" items="${requestScope.listEmployee}">      
                        <option value="${emp.no}">${emp.name} </option>
                    </c:forEach>
                </select>
            </p>
            <p>Skill </br>
                <c:if test="${sessionScope.listSkills != null}">
                    <select name="skills" multiple>
                        <c:forEach var="skill" items="${sessionScope.listSkills}">
                            <option value="${skill.id}">${skill.name}</option>
                        </c:forEach>
                    </select></br>
                    <small>Hold down Ctrl (Windows) or Command (Mac) to select multiple options.</small>
                </c:if>
            </p>
            <!--            <p>Skill </br>
            <c:if test="${sessionScope.listSkills != null}">
                <c:forEach var="skill" items="${sessionScope.listSkills}">
                    <label>
                        <input type="checkbox" name="skills" value="${skill.id}" />
                    ${skill.name}
                </label><br />
                </c:forEach>
            </c:if>
        </p>-->
            <p>Start Date <input type="date" name ="startDate" required=""/></p>
            <p>Salary <input type ="number" min="0" step="any" name="salary" required=""/></p>
            <p>Status 
                <input type="radio" name ="status" value ="1" checked="">Active
                <input type="radio" name ="status" value ="0">Inactive
            </p>
            Description<p> <textarea name="note" placeholder="Enter employee description" maxlength="100"></textarea></p>
            <p>Level <input type="number" name="level" min="1" required=""/></p>
            <input  type="hidden" name="action" value="CREATE_EMPLOYEE"/>
            <p><input type="submit" name="action" value="Add"/></p>
        </form>
    </c:if>
    <c:if test="${requestScope.Mssg != null}"> ${requestScope.Mssg}</c:if>
    <button onclick="window.location.href = 'MainController?action=index.html';">Go Back</button>
</body>
</html>
