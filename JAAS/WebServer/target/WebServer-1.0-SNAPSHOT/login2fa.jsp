<%-- 
    Document   : login2fa
    Created on : 01-mar-2021, 9:22:06
    Author     : CLwn1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
                <h1>Sign up</h1>
        <form action="QRcodeServlet" method="post">
            User:<br>
            <input type="text" name="user">
            <br>
            <input type="submit">
        </form> 
        <br />
        <a href="index.html">Tornar</a>
    </body>
</html>
