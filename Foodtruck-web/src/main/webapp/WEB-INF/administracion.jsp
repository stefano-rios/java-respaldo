

<%@page import="entities.Empleado"%>
<%try { %>
<%Empleado e = (Empleado) session.getAttribute("empleado"); %>

<% if (!e.getRoles().equalsIgnoreCase("Administrador")){
	response.sendRedirect("login");
	}
%>
<% }catch (Exception e){

	response.sendRedirect("login");
}%>


<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style/reset.css">
    <link rel="stylesheet" href="style/header.css">
    <link rel="stylesheet" href="style/admin.css">
    <script src="https://kit.fontawesome.com/b95dc486b7.js" crossorigin="anonymous"></script>
    <title>Foodtruck</title>
</head>
<body>
<jsp:include page="header.jsp"/>
    <main class="admin">
        <div class="admin__container">
            <h1>Administración</h1>
            <div class="item_container">
                
                <a href="listadopedidoporfecha"><div class="item">
                    <p class="item__icon"><i class="fa-solid fa-truck-fast"></i></p>
                    <p class="item__desc">Pedidos</p>
                </a>
                </div>
                
                <a href="menubebidaplato"><div class="item">
                    <p class="item__icon"><i class="fa-solid fa-utensils"></i></p>
                    <p class="item__desc">Productos</p>
                </a>
                </div>

                <a href="listadoempleados"><div class="item">
                    <p class="item__icon"><i class="fa-solid fa-suitcase"></i></p>
                    <p class="item__desc">Empleados</p>
                </a>
                </div>

                <a href="listadoclientes"><div class="item">
                    <p class="item__icon"><i class="fa-solid fa-person"></i></p>
                    <p class="item__desc">Clientes</p>
                </a>
                </div>
                
                
                           
            </div>
        </div>
    </main>
    
</body>
</html>

