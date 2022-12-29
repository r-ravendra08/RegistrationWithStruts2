<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="org.apache.struts2.ServletActionContext"%>
<%@page import="com.dao.RegisterDao"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profile Page</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.2/jspdf.min.js"></script>



<script type="text/javascript">
	$(document).ready(function() {

		var specialElementHandler = {
			"#editor" : function(element, renderer) {
				return true;
			}
		};
		$("#cmd").click(function() {
			var doc = new jsPDF();

			
			
			doc.fromHTML($("#target").html(), 80, 15, {
				"width" : 150,
				"elementHandlers" : specialElementHandler
				
			});
			doc.setFontSize(16)

			doc.setFont('courier')
			doc.setFontType('normal')
			doc.setTextColor(255, 0, 0)

			doc.text("---User Data---", 80, 10);
			doc.save("simple.pdf");

		});
	});
</script>
</head>
<%@ taglib uri="/struts-tags" prefix="s"%>

<body>
	<div class="text-center m-4" id="target">


		Welcome to Profile,<br>
		<hr>
		<%
			HttpSession sessio = ServletActionContext.getRequest().getSession(false);
			String email = (String) sessio.getAttribute("email");
			out.println("User Id : " + RegisterDao.getLoggedInUserDetails(email).getId() + "<br><hr>");
			out.println("Name : " + RegisterDao.getLoggedInUserDetails(email).getName() + "<br><hr>");
			out.print("Email Address : " + RegisterDao.getLoggedInUserDetails(email).getEmail() + "<br><hr>");
			out.print("Date & Time : " + RegisterDao.getLoggedInUserDetails(email).getDateTime() + "<br><hr>");
		%>
	</div>
	<center>
		<a href="logout">Logout</a>


		<button id="cmd" type="button" class="btn btn-primary">Generate PDF</button>
	</center>

</body>
</html>