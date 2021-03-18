<%-- 
    Document   : login2fa
    Created on : 04-mar-2021, 16:58:02
    Author     : CLwn1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Log in 2FA</title>
    </head>
    <body>
        <form action="LoginServlet" method="POST">
            <input type="text" name="user" />
            <input type="submit" value="Send" />
        </form>
    </body>
</html>
