<%-- 
    Document   : UpdateSkillPage
    Created on : Nov 27, 2024, 8:10:19 AM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Page</title>
    </head>
    <body>
        <h1>Update skill</h1>
        <form action= "MainController" method = "POST">
            <p><input type = "text" name= "id" value= "${param.id}" readonly=""></p>
            <p><input type = "text" name= "skillName" required = "" maxlength = "30" value = "${param.name}"></p>
            <p><textarea name = "note">${param.note} </textarea></p>
            <input type="hidden" name = "action" value="SAVE_SKILL"/>
            <p><input type = "submit" value = "Save" name = "action" /></p>

        </form>
        <button onclick="window.location.href = 'MainController?action=index.html';">Go Back</button>

        <c:if test="${requestScope.Mssg != null}">${requestScope.Mssg}</c:if>
    </body>
</html>
