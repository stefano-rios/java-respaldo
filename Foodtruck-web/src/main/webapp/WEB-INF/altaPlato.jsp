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
    <link rel="stylesheet" href="style/altaPlato.css">
    <title>Foodtruck</title>
    <% String mensaje = (String) request.getAttribute("mensaje"); %>
</head>
<body>
   <jsp:include page="header.jsp"/>
	<div class="form">
        <form action="altaplato" method="post" enctype="multipart/form-data">
            <div class="form__container">
                <h1>Nuevo Plato</h1>
                
                <div class="form__item">
                    <label>Nombre</label>
                    <input name="nombre">
                </div>
                  
                 <div class="form__item">
                    <label>Precio</label>
                    <input name="precio">
                </div>
                 <div class="form__item">
                    <label>Descripción</label>
                    <input name="descripcion">
                </div>
                
                <div class="form__item">
                    <label>Imagen</label>
                    <input name="imagen" type="file">
                </div>
                
                <p class="error"><%=mensaje%></p>
                 <button class="button" type="submit">Agregar</button>
               </div>
        </form>
        
    </div>
    
</body>
</html>

<% }catch (Exception e){

	response.sendRedirect("login");
}%>