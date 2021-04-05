<html>
<head>
    <%
    out.println(session.getAttribute("user"));
    String user = session.getAttribute("user").toString();
    pageContext.setAttribute("user", user);
%>
<title>Login Page</title>
<body bgcolor="white">
<img src="http://localhost:8080/WebServer/img/${user}.jpg"/>
<form method="POST" action="j_security_check"%> 
  <table border="0" cellspacing="5">
    <tr>
      <th align="right">Username:</th>
      <td align="left"><input type="text" name="j_username"></td>
    </tr>
    <tr>
      <th align="right">Password:</th>
      <td align="left"><input type="password" name="j_password"></td>
    </tr>
    <tr>
      <td align="right"><input type="submit" value="Log In"></td>
      <td align="left"><input type="reset"></td>
    </tr>
  </table>
</form>
</body>
</html>
