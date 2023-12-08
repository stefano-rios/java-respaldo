<% try { %>

<%@page import="entities.Empleado"%>
<%Empleado e = (Empleado) session.getAttribute("empleado"); %>

<% if (!e.getRoles().equalsIgnoreCase("Administrador")){
	response.sendRedirect("login");
	}
%>


<!DOCTYPE html>
<%@page import="entities.Bebida"%>
<%@page import="entities.Plato"%>
<%@page import="java.util.LinkedList"%>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style/reset.css">
    <link rel="stylesheet" href="style/header.css">
    <link rel="stylesheet" href="style/iniciarpedido.css">
    
<title>Foodtruck</title>

<% LinkedList<Plato> platos = (LinkedList<Plato>) request.getAttribute("listadoplatos");%>
<% LinkedList<Bebida> bebidas = (LinkedList<Bebida>) request.getAttribute("listadobebidas");%>

</head>
<body>
	 <jsp:include page="header.jsp"/>
	
        <div class="container">
            <h1>Platos</h1>
            <div class="productos">
				<%for (Plato plato : platos){ %>
				<div class ="producto">
                    <div class="producto__container" id="<%=plato.getNombre()%>">
                        <img class="imagen" src="<%=plato.getFoto()%>" id="<%=plato.getNombre()%>"/>
                        <p class="titulo" id="<%=plato.getNombre()%>"><%=plato.getNombre()%></p>
                        <p class="precio" id="<%=plato.getNombre()%>">$<%=plato.getPrecio()%></p>
                    </div>
                </div>
				<%} %>
				
            </div>
           </div>
          
          
          	<div class="container">
            <h1>Bebidas</h1>
            <div class ="productos">
				<%for (Bebida b : bebidas){ %>
				<div class ="producto">
                    <div class="producto__container" id="<%=b.getNombre()%>">
                        <img class="imagen"src="<%=b.getFoto()%>" id="<%=b.getNombre()%>"/>
                        <p class="titulo" id="<%=b.getNombre()%>"><%=b.getNombre()%></p>
                        <p class="precio" id="<%=b.getNombre()%>">$<%=b.getPrecio()%></p>
                    </div>
                </div>
                <%} %>
            
            </div>
           </div>
           
           	<div class="form">
        <form action="iniciarpedido" method="post">
			
			<% for (Plato plato : platos){ %>
				<label><%=plato.getNombre()%></label>
				<input type="checkbox" name="checkboxplato" value="<%=plato.getId()%>" id="<%=plato.getNombre()%>">
			<%} %>
			
			<% for (Bebida b : bebidas){ %>
				<label><%=b.getNombre()%></label>
				<input type="checkbox" name="checkboxbebida" value="<%=b.getId()%>" id="<%=b.getNombre()%>">
			<%} %>
            
            
                <button class="button" type="submit">Crear pedido</button>	
                 
        </form>
	</div> 
           



   

    <script src="js/iniciarpedido.js"></script>
</body>
</html>

<% }catch (Exception e){

	response.sendRedirect("login");
}%>