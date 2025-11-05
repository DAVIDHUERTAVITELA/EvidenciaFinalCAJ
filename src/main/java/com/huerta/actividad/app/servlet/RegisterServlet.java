package com.huerta.actividad.app.servlet;

import com.huerta.actividad.app.dao.UsuarioDAO;
import com.huerta.actividad.app.model.Persona;
import com.huerta.actividad.app.util.Validator;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombreCompleto = req.getParameter("nombreCompleto");
        String nombreUsuario = req.getParameter("nombreUsuario");
        String edadS = req.getParameter("edad");
        String sexo = req.getParameter("sexo");
        String estaturaS = req.getParameter("estatura");
        String password = req.getParameter("password");
        String passwordConf = req.getParameter("passwordConf");

        String error = null;
        if (nombreCompleto == null || nombreUsuario == null || edadS == null || estaturaS == null || password == null) {
            error = "Faltan datos requeridos.";
        } else {
            int edad;
            double estatura;
            try {
                edad = Integer.parseInt(edadS);
                estatura = Double.parseDouble(estaturaS);
            } catch (NumberFormatException nfe) {
                error = "Edad o estatura en formato inválido.";
                edad = -1; estatura = -1;
            }

            if (error == null) {
                Persona p = new Persona();
                p.setNombreCompleto(nombreCompleto);
                p.setNombreUsuario(nombreUsuario);
                p.setEdad(edad);
                p.setSexo(sexo);
                p.setEstatura(estatura);

                if (!Validator.validarRegistro(p)) {
                    error = "Edad debe ser >= 15 y estatura entre 1.0 y 2.5 metros.";
                } else if (!password.equals(passwordConf)) {
                    error = "Contraseñas no coinciden.";
                } else {
                    // hash de password
                    String hash = BCrypt.hashpw(password, BCrypt.gensalt());
                    try {
                        int id = usuarioDAO.guardarUsuario(p, hash);
                        if (id > 0) {
                            // registro exitoso -> redirect a login
                            resp.sendRedirect(req.getContextPath() + "/login?registered=1");
                            return;
                        } else {
                            error = "Error al guardar usuario.";
                        }
                    } catch (SQLException e) {
                        if (e.getMessage().contains("Duplicate")) {
                            error = "Nombre de usuario ya existe.";
                        } else {
                            e.printStackTrace();
                            error = "Error interno.";
                        }
                    }
                }
            }
        }

        req.setAttribute("error", error);
        req.getRequestDispatcher("/register.jsp").forward(req, resp);
    }
}
