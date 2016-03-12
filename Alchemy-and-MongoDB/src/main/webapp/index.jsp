
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alchemy and MongoDB services</title>
    </head>
    <body>
		<form action="IServlet" method="GET">
            <input type="text" name="imageURL" size="80">
			<input type="submit" value="Extract">
        </form> <br/>
		<%
			if (request.getAttribute("extractInfo") != null){
				out.println("<h3>" + request.getAttribute("extractInfo") + "</h3>");
			}
		%>
    </body>
</html>
