<%try { %>
<%@page import="entities.Empleado"%>
<%Empleado e = (Empleado) session.getAttribute("empleado");%>

<!DOCTYPE html>
<%@page import="entities.Bebida"%>
<%@page import="entities.Plato"%>
<%@page import="entities.LineaPedido"%>
<%@page import="entities.Pedido"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style/reset.css">
    <link rel="stylesheet" href="style/header.css">
    <link rel="stylesheet" href="style/altapedidodelivery.css">
    
<% Pedido p = (Pedido)session.getAttribute("pedido"); %>

    <title>Foodtruck</title>
</head>
<body>
    <jsp:include page="header.jsp"/>

    <main>
        <div class = "pedidos">
            <div class="pedido">
                <div class="pedido__header">
                    <div class="pedido__titulo">Pedido</div>
                </div>
        		
                <div class="pedido__content">
                   
                    <div class="lineas">
                       <%for (LineaPedido lp : p.getLineas()){ %>
                       
                       <%if (lp.getProducto() instanceof Plato){ %>
                       <%Plato plato = (Plato)lp.getProducto(); %>
                        <div class="linea">
                            <img class="imagen" src="<%=plato.getFoto()%>"/>
                            <div class="linea__content">
                               
                                <p class="linea__titulo"><%=plato.getNombre()%></p>
                                <p class="linea__desc"><%=plato.getDescripcion() %></p>
                            </div>
                            <p class="cantidad">x<%=lp.getCantidad()%></p>
                        </div>
                        <%} %>
                        
                        <%if (lp.getProducto() instanceof Bebida){ %>
                       <%Bebida bebida = (Bebida)lp.getProducto(); %>
                        <div class="linea">
                            <img class="imagen" src="<%=bebida.getFoto()%>"/>
                            <div class="linea__content">
                               
                                <p class="linea__titulo"><%=bebida.getNombre()%></p>
                                <p class="linea__desc">Litros: <%=bebida.getLitros()%></p>
                            </div>
                            <p class="cantidad">x<%=lp.getCantidad()%></p>
                        </div>
                        <%} %>
                        <%} %>
                    </div>
                    
                   <p class="total">Total: $<span class="numero"><%=p.getTotal()%></span></p>
                </div>
                 
            </div>
        </div>

        
        <form action="altapedidodelivery" method="POST" >
                <div class="form__container">
                    <h1>Datos del cliente</h1>
                    <div class="form__item">
                        <label>DNI</label>
                        <input name="dni" maxlength="8" minlength="8">
                    </div>
                    <div class="form__item">
                        <label>Nombre</label>
                        <input name="nombre">
                    </div>
                
                    <div class="form__item">
                        <label>Dirección</label>
                        <input name="direccion">
                    </div>
                    <button class="button" type="submit">Confirmar</button>	
                </div>      
            </form>
        

    </main>

    
</body>
</html>

<% }catch(Exception e){
	request.getRequestDispatcher("/login").forward(request,response);
}
%>
