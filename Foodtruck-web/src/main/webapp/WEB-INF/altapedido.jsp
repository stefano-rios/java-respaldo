<% try { %>

<%@page import="entities.Empleado"%>
<%Empleado e = (Empleado) session.getAttribute("empleado"); %>

<% if (!e.getRoles().equalsIgnoreCase("Administrador")){
	request.getRequestDispatcher("/login").forward(request,response);
	}
%>
<% }catch(Exception e){
	request.getRequestDispatcher("/login").forward(request,response);
}
%>


<!DOCTYPE html>
<%@page import="entities.LineaPedido"%>
<%@page import="java.util.LinkedList"%>
<%@page import="entities.Plato" %>
<%@page import="entities.Bebida" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style/reset.css">
    <link rel="stylesheet" href="style/header.css">
    <link rel="stylesheet" href="style/nuevopedido.css">
    <script src="https://kit.fontawesome.com/b95dc486b7.js" crossorigin="anonymous"></script>
	<%
    
    LinkedList<LineaPedido> lineas = (LinkedList<LineaPedido>) session.getAttribute("lineas");

    %>
    <title>Foodtruck</title>
</head>
<body>

 <jsp:include page="header.jsp"/>
 
    <div class="form">
        <form action="altapedido" method="post">
            <div class="form__container">
                <h1>Nuevo Pedido</h1>
                <div class="item__container">
                    <div class="item">
                        <i class="fa-solid fa-shop elegido" id="local"></i>
                        <input class="radio"type="radio" name="servicio" id="pres" value="local" checked>
                        <label>Local</label>
                    </div>

                    <div class="item">
                        <i class="fa-solid fa-truck" id="delivery"></i>
                        <input class="radio" type="radio" name="servicio" id="del" value="delivery">
                        <label>Delivery</label>
                    </div>
                </div>
                
                <div class="lineas">
                <%int total = 0; %>
                <%for(LineaPedido linea : lineas){ %>
                
                	<% if (linea.getProducto() instanceof Plato){%>
                		<% Plato plato = (Plato) linea.getProducto();%>
                		<%total += plato.getPrecio();%>
                		<div class="linea">
                        <div class="linea__item">
                            <img src="<%=plato.getFoto()%>"/>
                            <div class="linea__container">
                                <p class="linea__titulo"><%=plato.getNombre()%></p>
                                <p class="linea__desc"><%=plato.getDescripcion() %></p>
                                <p class="linea__precio">$<span class="precio"><%=plato.getPrecio()%></span></p>
                            </div>
                        </div>

                        <div class="cantidad__container">
                            <i class="fa-solid fa-minus"></i>
                            <input class="cantidad" name="cantidad" type="number" value="1" id="<%=plato.getNombre() %>" readonly="readonly"/>
                            <i class="fa-solid fa-plus"></i>
                        </div>
                		
                	<%} %>
                	
                	<% if (linea.getProducto() instanceof Bebida){%>
                		<% Bebida b = (Bebida) linea.getProducto();%>
                		<%total += b.getPrecio();%>
                		<div class="linea">
                        <div class="linea__item">
                            <img src="<%=b.getFoto()%>"/>
                            <div class="linea__container">
                                <p class="linea__titulo"><%=b.getNombre()%></p>
                                <p class="linea__desc"><%=b.getNombre() %></p>
                                <p class="linea__precio">$<span class="precio"><%=b.getPrecio()%></span></p>
                            </div>
                        </div>

                        <div class="cantidad__container">
                            <i class="fa-solid fa-minus"></i>
                            <input class="cantidad" name="cantidad"type="number" value="1" id="<%=b.getNombre() %>" readonly />
                            <i class="fa-solid fa-plus"></i>
                        </div>
                		
                	<%} %>
                	
                	
                
                    

                    </div>
                    <%} %>

                    
                </div>
                <p class="total">Total: $<span class="numero"><%=total%></span></p>
                <div>
                    
                    <button class="confirmar" id="confirmar" type="submit">Confirmar</button>	
                </div>
            </div>      
        </form>
    </div>
    <script src="js/nuevopedido.js" ></script>
</body>
</html>

