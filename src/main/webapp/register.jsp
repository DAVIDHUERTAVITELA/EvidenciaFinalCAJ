<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Registro</title></head>
<body>
<h2>Crear cuenta</h2>
<c:if test="${not empty error}"><div style="color:red">${error}</div></c:if>
<form method="post" action="${pageContext.request.contextPath}/register">
    Nombre completo: <input type="text" name="nombreCompleto" required /><br/>
    Nombre usuario: <input type="text" name="nombreUsuario" required /><br/>
    Edad: <input type="number" name="edad" min="15" required /><br/>
    Sexo: <select name="sexo"><option value="M">Hombre</option><option value="F">Mujer</option><option value="Otro">Otro</option></select><br/>
    Estatura (m): <input type="number" step="0.01" name="estatura" min="1.00" max="2.50" required /><br/>
    Contraseña: <input type="password" name="password" required /><br/>
    Confirmar: <input type="password" name="passwordConf" required /><br/>
    <button type="submit">Registrarme</button>
</form>
<a href="${pageContext.request.contextPath}/login">Volver a login</a>
</body>
</html>