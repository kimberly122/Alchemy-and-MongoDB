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
			if (request.getAttribute("extractInfo") != null){
				out.println("<h3>" + request.getAttribute("extractInfo") + "</h3>");
			}
		%>
		
		 <% 
        List<String> result = (List<String>) request.getAttribute("result");
         if (result == null) {
        	 result = new ArrayList<String>();
         }

         for (String rs : result) {
      %>
        	<div><%= rs %></div>
      <% } %>
    </body>
</html>
