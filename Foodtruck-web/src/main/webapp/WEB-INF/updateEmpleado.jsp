<%@page import="entities.Empleado"%>
<%@page import="entities.Empleado"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="style/reset.css">
    <link rel="stylesheet" href="style/header.css">
    <link rel="stylesheet" href="style/updateEmpleado.css">
    <script src="https://kit.fontawesome.com/b95dc486b7.js" crossorigin="anonymous"></script>
    
    
    <title>Foodtruck</title>
    <%
    if (session.getAttribute("empleado")!=null){
    
    Empleado em = (Empleado) session.getAttribute("empleado"); 

	if (!em.getRoles().equalsIgnoreCase("Administrador")){
	response.sendRedirect("login");//
	}
    }
	else {
		response.sendRedirect("login");//
	}
    
%>
    
    <% Empleado e = (Empleado) request.getAttribute("emp");%>
    <% String mensaje = (String) request.getAttribute("mensaje"); %>
</head>
<body>
 <jsp:include page="header.jsp"/>

    <div class="form" >
        <form action="empleadoeditar?dni=<%=e.getDni()%>" method="post">
            <div class="form__container">
                <h1>Modificar Empleado</h1>
                <div class="form__item">
                    <label>DNI</label>
                    <input name="dni" type="text" maxlength="8" value=<%=e.getDni() %>>
                </div>
                <div class="form__item">
                    <label>Nombre</label>
                    <input name="nombre" type="text" value="<%=e.getNombre()%>">
                </div>
                <div class="form__item">
                    <label>Turno</label>
                    <select name="turno">
                    	<option value="none" selected disabled hidden>Select an Option</option>
                        <option value="Tarde" <%if(e.getTurno().equalsIgnoreCase("Tarde")){ %><%="selected" %><%}%>>Tarde</option>
                        <option value="Noche"<%if(e.getTurno().equalsIgnoreCase("Noche")){ %><%="selected" %><%}%>>Noche</option>
                    </select>
                </div>
                
                 <div class="form__item">
                    <label>Rol</label>
                    <select name="rol">
                    <option value="none" selected disabled hidden>Seleccione una opción</option>
						<option value="Chef">Chef</option>
                        <option value="Delivery">Delivery</option>
                        <option value="Mozo">Mozo</option>
                    </select>
                </div>
                
                <div class="form__item">
                    <label>Contraseña</label>
                    <input name="password">
                </div>
            	<div><p class="error"><%=mensaje%></p></div>
                <button class="button" type="submit">Modificar</button>	
            </div>      
        </form>
    </div>
</div>
    
</body>
</html>

