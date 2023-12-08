<% try { %>

<%@page import="entities.Empleado"%>
<%Empleado e = (Empleado) session.getAttribute("empleado"); %>

<% if (!e.getRoles().equalsIgnoreCase("Administrador")){
	response.sendRedirect("login");
	}
%>
<%@page import="java.util.LinkedList"%>
<%@page import="entities.Bebida"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
   <link rel="stylesheet" href="style/header.css">
    <link rel="stylesheet" href="style/listadoBebidas.css">
    <script src="https://kit.fontawesome.com/b95dc486b7.js" crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>Foodtruck</title>

<%LinkedList<Bebida> Be = (LinkedList<Bebida>)request.getAttribute("listadoBebida"); %>
</head>
<body>
<jsp:include page="header.jsp"/>

<div>

	<div class="tabla">
		<div class="tabla__container">
		<h1>Listado de Bebidas</h1>
			<table>
			<thead>
			<tr>
				<th>Imagen</th>
				<th>Nombre</th>
				<th>Precio</th>
				<th>Litros</th>
				<th>Editar</th>
				<th>Borrar</th>
			</tr>
			</thead>
			<tbody>
			<% for (Bebida b : Be){ %>
				<tr>
				<td><img src="<%=b.getFoto() %>" width="50" height="50" border-radius="16"/></td>
					<td><%=b.getNombre()%></td>
					<td><%=b.getPrecio()%></td>
					<td><%=b.getLitros()%></td>
					
					<td><a href="editbebida?id=<%=b.getId()%>"><i class="fa-solid fa-pencil"></i></a></td>
					<td><a href="deletebebida?id=<%=b.getId()%>"><i class="fa-regular fa-circle-xmark"></i></a></td>
				</tr>
				
			<%} %>
			</tbody>
		</table>
		<a class="button" href="altabebida">Nueva bebida</a>
		</div>
	</div>

		
	</div>

</body>
</html>

<% }catch (Exception e){

	response.sendRedirect("login");
}%>