<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Login</title></head>
<body>
<h2>Iniciar sesión</h2>
<c:if test="${param.registered == '1'}"><div style="color:green">Registro exitoso. Inicia sesión.</div></c:if>
<c:if test="${not empty error}"><div style="color:red">${error}</div></c:if>
<form method="post" action="${pageContext.request.contextPath}/login">
    Usuario: <input type="text" name="nombreUsuario" required /><br/>
    Contraseña: <input type="password" name="password" required /><br/>
    <button type="submit">Ingresar</button>
</form>
<a href="${pageContext.request.contextPath}/register">Crear cuenta</a>
</body>
</html>