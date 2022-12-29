<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body>
	<script type="text/javascript">
		alert('login Success');
	</script>

<%@ taglib uri="/struts-tags" prefix="S"%>

	
<br/>Welcome, <S:property value="email"/>  
<a href="profile">Go to profile</a>


	

</body>
</html>