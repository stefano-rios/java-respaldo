<% try{ %>

<%@page import="entities.Empleado"%>
<%Empleado e = (Empleado) session.getAttribute("empleado"); %>

<% if (!e.getRoles().equalsIgnoreCase("Administrador")){
	response.sendRedirect("login");
	}
%>

<%@page import="java.util.LinkedList"%>
<%@page import="entities.Plato"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
   <link rel="stylesheet" href="style/header.css">
    <link rel="stylesheet" href="style/listadoPlatos.css">
    <script src="https://kit.fontawesome.com/b95dc486b7.js" crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>Foodtruck</title>

<%LinkedList<Plato> lp = (LinkedList<Plato>)request.getAttribute("listadoPlato"); %>
</head>
<body>
<jsp:include page="header.jsp"/>

<div>

	<div class="tabla">
		<div class="tabla__container">
		<h1>Listado de Platos</h1>
			<table>
			<thead>
			<tr>
				<th>Imagen</th>
				<th>Nombre</th>
				<th>Precio</th>
				<th>Descripción</th>
				<th>Editar</th>
				<th>Borrar</th>
			</tr>
			</thead>
			<tbody>
			<% for (Plato p : lp){ %>
				<tr>
					<td><img src="<%=p.getFoto() %>" width="50" height="50"/></td>
					<td><%=p.getNombre()%></td>
					<td><%=p.getPrecio()%></td>
					<td><%=p.getDescripcion()%></td>
					<td><a href="editplato?id=<%=p.getId()%>"><i class="fa-solid fa-pencil"></i></a></td>
					<td><a href="deleteplato?id=<%=p.getId()%>"><i class="fa-regular fa-circle-xmark"></i></a></td>
				</tr>
				
			<%} %>
			</tbody>
		</table>
		<a class="button" href="altaplato">Nuevo Plato</a>
		</div>
	</div>

		
		
	</div>

</body>
</html>

<% }catch (Exception e){

	response.sendRedirect("login");
}%>