<% try{ %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="style/reset.css">
<link rel="stylesheet" href="style/login.css">
<title>Foodtruck</title>
<% String mensaje = (String)request.getAttribute("mensaje");
   if(mensaje == null){
   	mensaje = "";
   }
%>

</head>
<body>
	<div class="login">
        <div class="login__container">
            <form action="login" method="post">
                <h1>Iniciar Sesión</h1>
                <div class="container">
                    <label for="dni">DNI</label>
                    <input class="input"type="text" maxlength="8" name="dni">
                </div>
                
                <div class="container">
                    <label for="password">Contraseña</label>
                    <input class="input" type="password" name="password">
                </div>
                <p class="error"><%=mensaje%></p>
                <button class="button" type="submit">Login</button>
            </form>
        </div>
        <a style=" margin-top:2rem;font-size:0.75rem;"href="listadopedido">Soy invitado</a>
	</div>
</body>
</html>

<% }catch (Exception e){

	response.sendRedirect("login");
}%>