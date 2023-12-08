<% try { %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%String mensaje = (String) request.getAttribute("mensaje"); %>
<%String servlet = (String) request.getAttribute("servlet"); %>
<meta charset="UTF-8">
<title>Foodtruck</title>
 <link rel="stylesheet" href="style/reset.css">
<link rel="stylesheet" href="style/error.css">
<link rel="stylesheet" href="style/header.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<main>
	<div class="container">
	<p class="error"><%=mensaje %></p>
	<%if (!servlet.equalsIgnoreCase("")){ %>
	<a href="<%=servlet%>" class="button">Volver</a>
	<%} %>
	</div>
	
</main>

</body>
</html>

<% }catch (Exception e){

	response.sendRedirect("login");
}%>