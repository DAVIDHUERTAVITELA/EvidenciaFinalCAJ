package com.huerta.actividad.app.controllers;

import java.io.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h1>Información del Estudiante</h1>");
        out.println("<p><strong>Nombre:</strong> DAVID HUERTA VITELA</p>");
        out.println("<p><strong>Matrícula:</strong> 03074239</p>");
        out.println("<p><strong>Escuela:</strong> UNIVERSIDAD TECMILENIO</p>");
        out.println("<p><strong>Materia:</strong> COMPUTACIÓN AVANZADA DE JAVA</p>");
        out.println("<p><strong>Profesor:</strong> José Alfredo Jiménez Hernández</p>");
        out.println("</body></html>");
    }
}