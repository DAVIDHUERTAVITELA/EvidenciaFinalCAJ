<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.huerta.actividad.app.model.Persona" %>
<%
    Persona p = (Persona) session.getAttribute("usuario");
    if (p == null) {
        response.sendRedirect(request.getContextPath() + "/login");
        return;
    }
%>
<html><head><title>Calcular IMC</title></head><body>
<h2>Calculadora de IMC</h2>
<p>Usuario: <strong><%= p.getNombreCompleto() %></strong> - Estatura: <%= p.getEstatura() %> m</p>
<c:if test="${not empty error}"><div style="color:red">${error}</div></c:if>
<form method="post" action="${pageContext.request.contextPath}/imc">
    Peso (kg): <input type="number" name="peso" step="0.1" min="0.1" required/><br/>
    <button type="submit">Calcular</button>
</form>
<a href="${pageContext.request.contextPath}/logout">Cerrar sesión</a>
<a href="${pageContext.request.contextPath}/historial.jsp">Ver historial</a>
</body></html>