<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="dto.Department"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Department Management Page</title>
        <link rel="stylesheet" type="text/css" href="style/department.css">
    </head>
    <body>
        <h1>List of Available Departments</h1>
        <c:if test="${sessionScope.listDepartments == null}">
            <c:redirect url = "index.html"></c:redirect>
        </c:if>
        <c:if test="${sessionScope.listDepartments != null}">
            <table id="departmentTable" class="custom-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Note</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="department" items="${sessionScope.listDepartments}">
                        <tr><form action="MainController" method="POST" style="display:inline;">                    
                        <td><input type="hidden" name="id" readonly="" value="${department.id}">${department.id}</td>
                        <td><input type = "text" name= "depName" required = "" maxlength = "30" value="${department.name}"/></td>
                        <td><p><textarea name = "note">${department.note} </textarea></p></td>
                        <input type="hidden" value="UPDATE_DEPARTMENT" name="action"/>
                        <td><input type="submit" value="Update" name="action" /></td>
                    </form>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</c:if> 
<c:if test ="${sessionScope.listDepartments == null}">
    <p>There are no available department</p>
</c:if>

<h1>Create new department</h1>
<form action= "MainController" method = "POST">
    Department<p><input type = "text" name= "depName" placeholder = "Enter new department" required = "" maxlength = "30"></p>
    Description<p><textarea name = "note" placeholder="Enter department description"></textarea></p>
    <input type="hidden" value="CREATE_DEPARTMENT" name="action"/>
    <p><input type = "submit" value = "Create" name = "action" /></p>
</form>
<button onclick="window.location.href = 'MainController?action=index.html';">Go Back</button>
<c:if test="${requestScope.Mssg != null}">${requestScope.Mssg}</c:if>
</body>
</html>
