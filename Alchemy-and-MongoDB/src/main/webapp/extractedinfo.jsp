<!DOCTYPE html>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Extracted Information</title>
    </head>
    <body>
		<h3> Information Extraction Complete </h3>
		<%
			if (request.getAttribute("age") != null){
				out.println("<h3>" + request.getAttribute("age") + "</h3>");
			}
		%>
		
		 <%
			if (request.getAttribute("gender") != null){
				out.println("<h3>" + request.getAttribute("gender") + "</h3>");
			}
		%>
    </body>
</html>
