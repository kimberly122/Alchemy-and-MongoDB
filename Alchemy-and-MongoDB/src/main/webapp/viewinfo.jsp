<!DOCTYPE html>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Age and Gender MongoDB</title>
    </head>
    <body>
		<%
			if (request.getAttribute("extractInfo") != null){
				out.println("<h3>" + request.getAttribute("extractInfo") + "</h3>");
			}
		%>
		
    </body>
</html>
