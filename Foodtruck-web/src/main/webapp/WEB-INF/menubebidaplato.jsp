<%try{ %>

<%@page import="entities.Empleado"%>
<%Empleado e = (Empleado) session.getAttribute("empleado"); %>

<% if (!e.getRoles().equalsIgnoreCase("Administrador")){
	response.sendRedirect("login");
	}
%>

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
            <h1>Seleccionar</h1>
            <div class="item_container">
                
               <a href="listadoplato"><div class="item">
                    <p class="item__icon"><i class="fa-solid fa-fish"></i></p>
                    <p class="item__desc">Plato</p>
                </a>
                </div>
                
                <a href="listadobebida"><div class="item">
                    <p class="item__icon"><i class="fa-solid fa-mug-saucer"></i></p>
                    <p class="item__desc">Bebida</p>
                </a>
                </div>
                
                           
            </div>
        </div>
    </main>
    
</body>
</html>

<% }catch (Exception e){

	response.sendRedirect("login");
}%>