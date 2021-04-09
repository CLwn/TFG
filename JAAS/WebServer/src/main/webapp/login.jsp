<html>
<head>
    <%
    String user = session.getAttribute("user").toString();
    pageContext.setAttribute("user", user);
%>
<title>Login Page</title>
<body bgcolor="white">
<img src="http://localhost:8080/WebServer/img/${user}.jpg"/>
<form method="POST" action="j_security_check"%> 
  <table border="0" cellspacing="5">
    <tr>
      <td align="left"><input type="hidden" value="${user}" name="j_username"></td>
    </tr>
    <tr>
      <td align="center"><input type="submit" value="Send information"></td>
    </tr>
  </table>
</form>
</body>
</html>
