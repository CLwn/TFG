<%-- 
    Document   : sign_up
    Created on : 12 ago. 2020, 18:27:57
    Author     : marc_
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign up</title>
    </head>
 <body>
        <h1>Sign up</h1>
        <form action="QRcodeServlet" method="post">
            User:<br>
            <input type="text" name="user">
            <br>
            Password:<br>
            <input type="password" name="password">
            <br>
            <input type="submit">
        </form> 
        <br />
        <a href="index.html">Tornar</a>
    </body>
</html>
