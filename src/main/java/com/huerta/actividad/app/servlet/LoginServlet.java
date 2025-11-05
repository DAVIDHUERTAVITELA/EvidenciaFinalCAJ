package com.huerta.actividad.app.servlet;


import com.huerta.actividad.app.dao.UsuarioDAO;
import com.huerta.actividad.app.model.Persona;

import org.mindrot.jbcrypt.BCrypt;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombreUsuario = req.getParameter("nombreUsuario");
        String password = req.getParameter("password");

        String error = null;
        if (nombreUsuario == null || password == null) {
            error = "Ingrese usuario y contraseña.";
        } else {
            try {
                String hash = usuarioDAO.getPasswordHashByUsername(nombreUsuario);
                if (hash == null || !BCrypt.checkpw(password, hash)) {
                    error = "Usuario o contraseña incorrectos.";
                } else {
                    Persona p = usuarioDAO.findByUsername(nombreUsuario);
                    // crear session
                    HttpSession session = req.getSession(true);
                    session.setAttribute("usuario", p);
                    resp.sendRedirect(req.getContextPath() + "/imc");
                    return;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                error = "Error interno.";
            }
        }
        req.setAttribute("error", error);
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }
}