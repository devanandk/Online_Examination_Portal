<%-- 
    Document   : anotherPage
    Created on : Jul 13, 2012, 2:49:47 PM
    Author     : Devanand
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Examination Portal</title>
        
    </head>
    <body>
         <%
            session.setAttribute("Username", null);
            session.setAttribute("Privilage", null);
            session.setAttribute("UserId", null);
            session.invalidate();
            %>
        <h2>You have been logged out successfully</h2>
            <br/>
            <a href="index.html">Click here to login again...</a>
    </body>
</html>
