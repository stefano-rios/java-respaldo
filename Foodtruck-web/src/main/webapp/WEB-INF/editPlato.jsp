<% try { %>

<%@page import="entities.Empleado"%>
<%Empleado e = (Empleado) session.getAttribute("empleado"); %>

<% if (!e.getRoles().equalsIgnoreCase("Administrador")){
	response.sendRedirect("login");
	}
%>

<%@page import="entities.Plato"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style/reset.css">
    <link rel="stylesheet" href="style/header.css">
    <link rel="stylesheet" href="style/altaPlato.css">
    <title>Foodtruck</title>
    
    <% Plato plato = (Plato) request.getAttribute("plato");%>
    <% String mensaje = (String) request.getAttribute("mensaje"); %>
</head>
<body>
    <jsp:include page="header.jsp"/>
 
	<div class="form">
        <form action="editplato?id=<%=plato.getId()%>" method="post" enctype="multipart/form-data">
            <div class="form__container">
                <h1>Editar Plato</h1>
 
 				 <div class="form__item">
                    <label>Nombre</label>
                    <input name="nombre" value="<%=plato.getNombre() %>">
                </div>
 
                <div class="form__item">
                    <label>Precio</label>
                    <input name="precio" value=<%= plato.getPrecio() %>>
                </div>
                 
                 <div class="form__item">
                    <label>Descripción</label>
                    <input name="descripcion" value="<%=plato.getDescripcion() %>">
                </div>
                
                <div class="form__item">
                    <label>Imagen</label>
                    <input name="imagen" type="file" value="<%=plato.getFoto()%>">
                </div>
               	<p class="error"><%=mensaje%></p>
                 <button class="button" type="submit">Editar</button>
               </div>
        </form>
        
    </div>
    
</body>
</html>

<% }catch (Exception e){

	response.sendRedirect("login");
}%>