<%-- 
    Document   : SkillManagementPage
    Created on : Nov 26, 2024, 7:48:48 AM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="dto.Skill"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Skill Management Page</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/skill.css">
    </head>
    <body>
        <h1>List of Available Skills</h1>

        <c:if test="${empty sessionScope.listSkills}">
            <p>No skills available to display.</p>
        </c:if>

        <c:if test="${not empty sessionScope.listSkills}">
            <table id="skillsTable" class="custom-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Note</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="skill" items="${sessionScope.listSkills}">
                        <tr>
                    <form action="MainController" method="POST">
                        <input type="hidden" value="${skill.id}" name="id" />
                        <input type="hidden" value="${skill.name}" name="name" />
                        <input type="hidden" value="${skill.note}" name="note" />
                        <input type="hidden" value="UPDATE_SKILL" name="action" />
                        <td>${skill.id}</td>
                        <td>${skill.name}</td>
                        <td>${skill.note}</td>
                        <td>
                            <input type="submit" value="Update" />
                        </td>
                    </form>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</c:if>


<h1>Create New Skill</h1>
<form action="MainController" method="POST">
    <label>Skill:</label> </br>
    <input type="text" name="skillName" placeholder="Enter new skill name" required maxlength="30">
    <br>
    <label>Description:</label> </br>
    <textarea name="note" placeholder="Enter skill description"></textarea>
    <br> 
    <input type="hidden" value="CREATE_SKILL" name="action" />
    <input type="submit" value="Create" />
</form>
</br>
<button onclick="window.location.href = 'MainController?action=index.html';">Go Back</button>
<c:if test="${not empty requestScope.Mssg}">
    <p>${requestScope.Mssg}</p>
</c:if>
</body>
</html>
