<%-- 
    Document   : error
    Created on : 6 ago. 2020, 20:31:43
    Author     : clwn1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
    </head>
    <body bgcolor="white">
Invalid user name and/or password, please try
<a href='<%= response.encodeURL(application.getContextPath() + "/index.jsp") %>'>again</a>.
</body>
</html>



