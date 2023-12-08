<% try { %>

<%@page import="entities.Empleado"%>
<%Empleado e = (Empleado) session.getAttribute("empleado"); %>

<% if (!e.getRoles().equalsIgnoreCase("Administrador")){
	response.sendRedirect("login");
	}
%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style/reset.css">
    <link rel="stylesheet" href="style/header.css">
    <link rel="stylesheet" href="style/altaEmpleado.css">
     <%String mensaje = (String) request.getAttribute("mensaje"); %>
    <title>Foodtruck</title>
</head>
<body>
<jsp:include page="header.jsp"/>
	<div class="form">
        <form action="altaempleado" method="post">
            <div class="form__container">
                <h1>Nuevo Empleado</h1>
                <div class="form__item">
                    <label>DNI</label>
                    <input name="dni" maxlength="8" minlength="8">
                </div>
                <div class="form__item">
                    <label>Nombre</label>
                    <input name="nombre">
                </div>
                <div class="form__item">
                    <label>Turno</label>
                    <select name="turno">
                        <option value="Tarde">Tarde</option>
                        <option value="Noche">Noche</option>
                    </select>
                </div>

                <div class="form__item">
                    <label>Rol</label>
                    <select name="rol">
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
                <button class="button" type="submit">Agregar</button>	
                
            </div>      
        </form>
    </div>
    
</body>
</html>

<% }catch (Exception e){

	response.sendRedirect("login");
}%>