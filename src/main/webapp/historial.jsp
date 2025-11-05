<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Historial de IMC</title></head>
<body>
<h2>Historial de mediciones</h2>
<div id="historial"></div>

<script>
    fetch('${pageContext.request.contextPath}/api/mediciones', { credentials: 'same-origin' })
        .then(res => {
            if (res.status === 401) { window.location = '${pageContext.request.contextPath}/login'; }
            return res.json();
        })
        .then(data => {
            if (data && data.length) {
                let html = '<table border="1"><tr><th>Fecha</th><th>Peso (kg)</th><th>IMC</th><th>Categoría</th></tr>';
                data.forEach(r => {
                    html += `<tr><td>${r.fechaMedicion}</td><td>${r.peso}</td><td>${r.imc}</td><td>${r.categoria}</td></tr>`;
                });
                html += '</table>';
                document.getElementById('historial').innerHTML = html;
            } else {
                document.getElementById('historial').innerHTML = '<p>No hay mediciones todavía.</p>';
            }
        }).catch(e => {
        document.getElementById('historial').innerHTML = '<p>Error al cargar historial</p>';
        console.error(e);
    });
</script>
<a href="${pageContext.request.contextPath}/imc">Registrar nueva medición</a>
<a href="${pageContext.request.contextPath}/logout">Cerrar sesión</a>
</body>
</html>