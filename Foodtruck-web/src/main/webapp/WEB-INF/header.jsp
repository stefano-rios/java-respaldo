
<%@page import="entities.Empleado"%>
<% Empleado e = (Empleado) session.getAttribute("empleado"); %>

<%if(e == null){
	response.sendRedirect("login");	
}
	%>


<header>
        <div class="header">
            <div class="header__container">
            
            <%if (e.getRoles().equalsIgnoreCase("Administrador")) {%>
            
                <nav><a href="iniciarpedido"><img class="img__header" src="img/logo.png"/></a></nav>
                <nav><a href="iniciarpedido">Iniciar pedido</a></nav>
                <nav><a href="listadopedido">Pedidos</a></nav>
                <nav><a href="menuadmin">Administración</a></nav>
                
               <%} %>
               
               <%if (e.getRoles().equalsIgnoreCase("Delivery")) {%>
            
                <nav><a href="listadopedido"><img class="img__header" src="img/logo.png"/></a></nav>
                <nav><a href="listadopedido">Pedidos</a></nav>
          	  	
               <%} %>
                
            </div>
            <%if (e.getRoles().equalsIgnoreCase("Administrador") || e.getRoles().equalsIgnoreCase("Delivery")){ %>
          	<nav style="margin-left:auto; align-self:center;"><a href="logout">Cerrar Sesión</a></nav>
          	<%} %>
        </div>
   </header>