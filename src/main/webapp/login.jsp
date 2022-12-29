<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
<STYLE type="text/css">
.errorMessage {
	color: red;
}
</STYLE>
</head>
<body>
	<center>
		<h1>Login Page</h1>
		<s:form action="loginprocess">
			<s:textfield name="email" label="Email"></s:textfield>
			<s:password name="password" label="Password"></s:password>
			<s:submit value="Login"></s:submit>
		</s:form>
		
		No Account <a href="register.jsp">Register Here</a>
	</center>

</body>
</html>