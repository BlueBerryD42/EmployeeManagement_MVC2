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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Skill Management Page</title>
        <link rel="stylesheet" type="text/css" href="style/skill.css">
    </head>
    <body>
        <!--        This is a place for displaying all skill-->
        <h1>List of available skills</h1>
        <c:if test="${requestScope.listSkills != null}">
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
                    <c:forEach var="skill" items="${requestScope.listSkills}">
                        <tr>
                    <form action="MainController" method="POST">
                        <input type="hidden" value="${skill.id}" name="id"/>
                        <input type="hidden" value="${skill.name}" name="name"/>
                        <input type="hidden" value="${skill.note}" name="note"/>
                        <input type="hidden" value="Update" name="action"/>
                        <td>${skill.id}</td>
                        <td>${skill.name}</td>
                        <td>${skill.note}</td>
                        <td>
                            <input 
                                type="submit" value="Update"/>
                        </td>
                    </form>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</c:if>


<!--        This is form for creating skill-->

<h1>Create new skill</h1>
<form action= "MainController" method = "POST">
    Skill<p><input type = "text" name= "skillName" placeholder = "Enter new skill name" required = "" maxlength = "30"></p>
    Description<p><textarea name = "note" placeholder="Enter skill description"></textarea></p>
    <p><input type = "submit" value = "Create" name = "action" /></p>
</form>
<c:if test="${requestScope.Mssg != null}"> ${requestScope.Mssg.mssg}</c:if>
</body>
</html>
