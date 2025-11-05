🧾 Acerca de

La Aplicación Web para el Cálculo del IMC permite a los usuarios registrarse, iniciar sesión y calcular su Índice de Masa Corporal, almacenando un historial de mediciones para monitorear su progreso a lo largo del tiempo.

El sistema fue desarrollado en Java EE, bajo la arquitectura MVC (Modelo–Vista–Controlador), utilizando Servlets, JSP y un servicio REST para la consulta de datos.
La base de datos registra los datos del usuario (nombre, edad, sexo, estatura, credenciales) y las mediciones realizadas, garantizando una experiencia segura, usable y funcional.

El objetivo principal de esta aplicación es fomentar el autocontrol de la salud física y servir como herramienta práctica para el aprendizaje del desarrollo web profesional con Java.

🧩 Proyecto
🧠 Arquitectura general

La aplicación sigue una estructura modular MVC complementada con REST, que facilita la escalabilidad, el mantenimiento y la reutilización de código.

Usuario → JSP (Vista)
        → Servlet (Controlador)
        → Service (Negocio)
        → DAO (Base de datos)
        → REST (Historial)

📦 Estructura de paquetes
com.imcapp
├── controller/
│   ├── LoginServlet.java
│   ├── RegisterServlet.java
│   ├── IMCServlet.java
│   └── LogoutServlet.java
│
├── rest/
│   └── IMCHistoryResource.java
│
├── model/
│   ├── Usuario.java
│   └── MedicionIMC.java
│
├── dto/
│   └── IMCDTO.java
│
├── dao/
│   ├── ConexionBD.java
│   ├── UsuarioDAO.java
│   └── MedicionIMCDAO.java
│
├── service/
│   ├── IIMCService.java
│   ├── IMCService.java
│   ├── UsuarioService.java
│   └── Validator.java
│
├── util/
│   ├── HashUtil.java
│   ├── FechaUtil.java
│   └── ResponseUtil.java
│
└── exception/
    └── ValidationException.java

🗃️ Diseño de base de datos
Tabla: usuarios
Campo	Tipo	Descripción
id_usuario	INT (PK, AI)	Identificador único
nombre_completo	VARCHAR(100)	Nombre real
nombre_usuario	VARCHAR(50) UNIQUE	Usuario de login
edad	INT	Edad (>15 años)
sexo	ENUM('M','F','Otro')	Sexo
estatura	DECIMAL(4,2)	Altura en metros (1.00–2.50)
password_hash	VARCHAR(255)	Contraseña cifrada
fecha_registro	DATETIME	Fecha de alta
Tabla: mediciones_imc
Campo	Tipo	Descripción
id_medicion	INT (PK, AI)	Identificador de medición
id_usuario	INT (FK)	Relación con usuario
peso	DECIMAL(5,2)	Peso actual
imc	DECIMAL(5,2)	Resultado calculado
categoria	VARCHAR(30)	Clasificación OMS
fecha_medicion	DATETIME	Fecha de registro

Relación:
usuarios (1) ───< (N) mediciones_imc

⚙️ Diagrama de clases (resumen)
+-------------------+        +-------------------+
|      Usuario      |        |   MedicionIMC     |
+-------------------+        +-------------------+
| - id_usuario      |        | - id_medicion     |
| - nombre_usuario  |        | - peso            |
| - edad, sexo      |        | - imc, categoria  |
| - estatura        |        | - fecha_medicion  |
+-------------------+        +-------------------+
           ↑ 1                  ↑ N
           └───────── relación ────────────────┘

💻 Interfaz de usuario (vista JSP)
1. Login (index.jsp)

Permite al usuario iniciar sesión con su nombre de usuario y contraseña.
Sin sesión activa → no puede calcular su IMC.

2. Registro (registro.jsp)

Formulario con validaciones:

Edad ≥ 15 años

Estatura entre 1.00m y 2.50m

Contraseña obligatoria

3. Panel principal (principal.jsp)

Muestra:

IMC actual del usuario

Tabla con su historial de mediciones

Botón para registrar nueva medición

4. Cálculo (calcular.jsp)

Solicita:

Peso actual (> 0)

Calcula IMC = peso / estatura²

Guarda la medición en la BD

Muestra categoría (bajo peso, normal, sobrepeso, obesidad)

5. Historial (principal.jsp)

Los datos se obtienen desde:

GET /api/historial/{nombre_usuario}


Devuelve JSON con todas las mediciones, consumido mediante AJAX.

🧭 Guías
🔧 Configuración del entorno

Requisitos:

Java 17 o superior

Apache Tomcat 10+

MySQL 8.0

JDBC Driver para MySQL

Maven o IntelliJ IDEA / Eclipse

Pasos:

Crear base de datos imcdb

Ejecutar script SQL de creación de tablas.

Configurar ConexionBD.java con tus credenciales:

private static final String URL = "jdbc:mysql://localhost:3306/imcdb";
private static final String USER = "root";
private static final String PASS = "tu_password";


Desplegar el proyecto en Tomcat (http://localhost:8080/IMCApp)

🧮 Uso de la aplicación

Registro: Crear cuenta desde registro.jsp.

Inicio de sesión: Ingresar usuario y contraseña.

Cálculo: Ir a “Nueva medición”, ingresar peso → ver resultado.

Historial: Se mostrará automáticamente en tabla con fecha e IMC.

Cerrar sesión: Botón “Salir” limpia la sesión.

🔒 Seguridad y validaciones

Contraseñas cifradas con BCrypt (HashUtil.java).

Validación de edad y estatura en Validator.java.

Solo usuarios autenticados pueden acceder a IMCServlet o /api/historial.

Datos transmitidos mediante HTTPS (recomendado).

🧠 Ejemplo de flujo
Registro → Login → Calcular IMC → Guardar → Consultar Historial (REST)

📜 Autores y créditos

Desarrollador: David Huerta Vitela

Especialidad: Informática / Ciencias de la Computación

Framework: Java EE (Servlets, JSP, RESTful API)

Base de datos: MySQL

Arquitectura: MVC + REST + DAO
