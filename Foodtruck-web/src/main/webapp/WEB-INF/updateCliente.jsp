<%try{ %>

<%@page import="entities.Empleado"%>
<%Empleado e = (Empleado) session.getAttribute("empleado"); %>

<% if (!e.getRoles().equalsIgnoreCase("Administrador")){
	response.sendRedirect("login");
	}
%>


<%@page import="entities.Cliente"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="style/reset.css">
    <link rel="stylesheet" href="style/header.css">
    <link rel="stylesheet" href="style/updateCliente.css">
    <script src="https://kit.fontawesome.com/b95dc486b7.js" crossorigin="anonymous"></script>
    <title>Foodtruck</title>
    
    <% Cliente c = (Cliente) request.getAttribute("cli");%>
    <% String mensaje = (String) request.getAttribute("mensaje"); %>
</head>
<body>
     <jsp:include page="header.jsp"/>

    <div class="form" >
    <p class="error"><%=mensaje%></p>
        <form action="clienteeditar?id=<%=c.getDni()%>"  method="post">
            <div class="form__container">
                <h1>Modificar Cliente</h1>
                <div class="form__item">
                    <label>DNI</label>
                    <input name="dni" type="text" maxlength="8" value=<%=c.getDni() %>>
                </div>
                <div class="form__item">
                    <label>Nombre</label>
                    <input name="nombre" type="text" value="<%=c.getNombre()%>">
                </div>
                
                <div class="form__item">
                    <label>Dirección</label>
                    <input name="direccion" type="text" value="<%=c.getDireccion()%>">
                    
                </div>
                 
                <button class="button" type="submit">Modificar</button>	
            </div>      
        </form>
    </div>

    
</body>
</html>

<% }catch (Exception e){

	response.sendRedirect("login");
}%>