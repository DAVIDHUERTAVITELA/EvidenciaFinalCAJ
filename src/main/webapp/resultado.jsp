<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.huerta.actividad.app.model.IMCDTO" %>
<%
    IMCDTO res = (IMCDTO) request.getAttribute("resultado");
%>
<html><head><title>Resultado</title></head><body>
<h2>Resultado de IMC</h2>
<p>Tu IMC es: <strong><%= res.getImc() %></strong></p>
<p>Clasificación: <strong><%= res.getCategoria() %></strong></p>
<p>Fecha: <%= res.getFechaMedicion() %></p>

<form action="${pageContext.request.contextPath}/imc" method="get">
    <button type="submit">Registrar otra medición</button>
</form>
<a href="${pageContext.request.contextPath}/historial.jsp">Ver historial completo</a>
<a href="${pageContext.request.contextPath}/logout">Cerrar sesión</a>
</body></html>