<%-- 
    Document   : UpdateSkillPage
    Created on : Nov 27, 2024, 8:10:19 AM
    Author     : ASUS
--%>

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
            <p><input type = "text" name= "id" value= "<%= request.getParameter("id")%>" readonly=""></p>
            <p><input type = "text" name= "skillName" required = "" maxlength = "30" value = "<%= request.getParameter("name")%>"></p>
            <p><textarea name = "note"><%= request.getParameter("note")%> </textarea></p>
            <p><input type = "submit" value = "Save" name = "action" /></p>
        </form>
        <%
            String s = (String) request.getAttribute("Mssg");
            if (s != null) {
                out.print(s);
            }
        %>
    </body>
</html>
