package com.huerta.actividad.app.servlet;

import com.google.code.Gson;
import com.huerta.actividad.app.dao.MedicionDAO;
import com.huerta.actividad.app.model.IMCDTO;
import com.huerta.actividad.app.model.Persona;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/api/mediciones")
public class ApiMedicionesServlet extends HttpServlet {
    private MedicionDAO medicionDAO = new MedicionDAO();
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession s = req.getSession(false);
        resp.setContentType("application/json; charset=UTF-8");
        if (s == null || s.getAttribute("usuario") == null) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.getWriter().write("{\"error\":\"No autenticado\"}");
            return;
        }
        Persona p = (Persona) s.getAttribute("usuario");
        try {
            List<IMCDTO> lista = medicionDAO.obtenerHistorial(p.getId());
            resp.getWriter().write(gson.toJson(lista));
        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\":\"Error interno\"}");
            e.printStackTrace();
        }
    }
}