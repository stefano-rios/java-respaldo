<%try{ %>

<%@page import="entities.Empleado"%>
<%Empleado e = (Empleado) session.getAttribute("empleado"); %>



<!DOCTYPE html>
<%@page import="entities.Bebida"%>
<%@page import="entities.Plato"%>
<%@page import="entities.Cliente"%>
<%@page import="entities.LineaPedido"%>
<%@page import="entities.Pedido"%>
<%@page import="java.util.LinkedList"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
     <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style/reset.css">
    <link rel="stylesheet" href="style/header.css">
    <link rel="stylesheet" href="style/listadopedidos.css">
    
<%LinkedList<Pedido> pedidos = (LinkedList<Pedido>)request.getAttribute("pedidos"); %>


    <title>Foodtruck</title>
</head>
<body>
  <jsp:include page="header.jsp"/>
	    <div>
	    		<h1>Pedidos</h1>             	    
        </div>
<main>
        
       <%-- Para delivery --%>
      <%if ( e.getRoles().equalsIgnoreCase("Delivery") ){ %>
      
      	 <% for(Pedido pedido : pedidos){ %>
      	 
      	 	  <%if (pedido.getTipoPedido().equals("Delivery") && pedido.getEstado().equals("En camino")){ %>
      	 		
      	 		<div class="pedido">
                <div class="pedido__header">
                    <div class="nro"><%=pedido.getId()%></div>      
                </div>
                
                
                <%if(pedido.getTipoPedido().equals("Delivery")) {%>
                	 <%Cliente cli = pedido.getCliente();%>
                	 <p class="nombre"><%= cli.getNombre()%></p>
                	 <p class="direccion"><%=cli.getDireccion() %></p>
                <%} %>
                <span class="espacio"></span>
                
                <div class="pedido__content">
                    <div class="lineas">
                    
                    <% for (LineaPedido lp : pedido.getLineas()){ %>
                    
                    
                    <%if(lp.getProducto() instanceof Plato){ %>
                    <%Plato plato = (Plato) lp.getProducto();%>
                        <div class="linea">
                            <div class="linea__content">
                                <p class="linea__titulo"><%=plato.getNombre()%></p>
                                <p class="linea__desc"><%=plato.getDescripcion()%></p>
                            </div>
                            <p class="cantidad">x<%=lp.getCantidad() %></p>
                        </div>
                        <%} %>
                        
                        <%if(lp.getProducto() instanceof Bebida){ %>
                    	<%Bebida b = (Bebida) lp.getProducto();%>
                        <div class="linea">
                            <div class="linea__content">
                                <p class="linea__titulo"><%=b.getNombre()%></p>
                                <p class="linea__desc"> <%=b.getLitros()%></p>
                            </div>
                            <p class="cantidad">x<%=lp.getCantidad() %></p>
                        </div>
                        <%} %>
    					<%} %> 
                  
                    </div>
                    
                </div>
                
                <% if(pedido.getTipoPedido().equals("Delivery") && pedido.getEstado().equalsIgnoreCase("En preparación")){ %>
                <a href="enviarpedido?nro=<%=pedido.getId() %>" class="button">Enviar</a>
                <% } else if(pedido.getEstado().equalsIgnoreCase("En camino") || pedido.getTipoPedido().equals("Presencial")) { %>
                <a href="entregarpedido?nro=<%=pedido.getId() %>" class="entregar">Entregar</a>
 				<%} %>
            </div>
      	 	
      	 <% } %>
      	 
     <% }} %>
     
     <%-- Para Invitado --%>
     

     
      <%if ( e.getRoles().equalsIgnoreCase("Invitado") ){ %>
      	
      	<% for(Pedido pedido : pedidos){ %>
        
        <%if (pedido.getEstado().equals("En preparación") || pedido.getEstado().equals("En camino")){ %>

            <div class="pedido">
                <div class="pedido__header">
                    <div class="nro"><%=pedido.getId()%></div>      
                </div>
                
                
                <%if(pedido.getTipoPedido().equals("Delivery")) {%>
                	 <%Cliente cli = pedido.getCliente();%>
                	 <p class="nombre"><%= cli.getNombre()%></p>
                <%} %>
                	<p class="direccion"><%=pedido.getEstado() %></p>
                <span class="espacio"></span>
                
                <div class="pedido__content">
                    <div class="lineas">
                    
                    <% for (LineaPedido lp : pedido.getLineas()){ %>
                    
                    
                    <%if(lp.getProducto() instanceof Plato){ %>
                    <%Plato plato = (Plato) lp.getProducto();%>
                        <div class="linea">
                            <div class="linea__content">
                                <p class="linea__titulo"><%=plato.getNombre()%></p>
                                <p class="linea__desc"><%=plato.getDescripcion()%></p>
                            </div>
                            <p class="cantidad">x<%=lp.getCantidad() %></p>
                        </div>
                        <%} %>
                        
                        <%if(lp.getProducto() instanceof Bebida){ %>
                    	<%Bebida b = (Bebida) lp.getProducto();%>
                        <div class="linea">
                            <div class="linea__content">
                                <p class="linea__titulo"><%=b.getNombre()%></p>
                                <p class="linea__desc"> <%=b.getLitros()%></p>
                            </div>
                            <p class="cantidad">x<%=lp.getCantidad() %></p>
                        </div>
                        <%} %>
    					<%} %> 
                  
                    </div>
                    
                </div>
            </div>
 			<% }} %>
            
            
        </div>
      
      		
      	 <% }%>
      
       <%-- Para administrador --%>
        
        
        
       <%if ( e.getRoles().equalsIgnoreCase("Administrador") ){ %>
        
        <% for(Pedido pedido : pedidos){ %>
        
        <%if (pedido.getEstado().equals("En preparación") || pedido.getEstado().equals("En camino")){ %>

            <div class="pedido">
                <div class="pedido__header">
                    <div class="nro"><%=pedido.getId()%></div>      
                </div>
                
                
                <%if(pedido.getTipoPedido().equals("Delivery")) {%>
                	 <%Cliente cli = pedido.getCliente();%>
                	 <p class="nombre"><%= cli.getNombre()%></p>
                	 <p class="direccion"><%=cli.getDireccion() %></p>
                <%} %>
                <span class="espacio"></span>
                
                <div class="pedido__content">
                    <div class="lineas">
                    
                    <% for (LineaPedido lp : pedido.getLineas()){ %>
                    
                    
                    <%if(lp.getProducto() instanceof Plato){ %>
                    <%Plato plato = (Plato) lp.getProducto();%>
                        <div class="linea">
                            <div class="linea__content">
                                <p class="linea__titulo"><%=plato.getNombre()%></p>
                                <p class="linea__desc"><%=plato.getDescripcion()%></p>
                            </div>
                            <p class="cantidad">x<%=lp.getCantidad() %></p>
                        </div>
                        <%} %>
                        
                        <%if(lp.getProducto() instanceof Bebida){ %>
                    	<%Bebida b = (Bebida) lp.getProducto();%>
                        <div class="linea">
                            <div class="linea__content">
                                <p class="linea__titulo"><%=b.getNombre()%></p>
                                <p class="linea__desc"> <%=b.getLitros()%></p>
                            </div>
                            <p class="cantidad">x<%=lp.getCantidad() %></p>
                        </div>
                        <%} %>
    					<%} %> 
                  
                    </div>
                    
                </div>
                
                <% if(pedido.getTipoPedido().equals("Delivery") && pedido.getEstado().equalsIgnoreCase("En preparación")){ %>
                <a href="enviarpedido?nro=<%=pedido.getId() %>" class="button">Enviar</a>
                <% } else if(pedido.getEstado().equalsIgnoreCase("En camino") || pedido.getTipoPedido().equals("Presencial")) { %>
                <a href="entregarpedido?nro=<%=pedido.getId() %>" class="entregar">Entregar</a>
 				<%} %>
            </div>
 			<% }} %>
      
        <% } %>
    </main>

</body>
</html>

<% }catch (Exception e){

	response.sendRedirect("login");
}%>