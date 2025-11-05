package com.huerta.actividad.app.servlet;

import com.huerta.actividad.app.model.IMCDTO;
import com.huerta.actividad.app.model.Persona;
import com.huerta.actividad.app.service.IMCServiceImpl;
import com.huerta.actividad.app.service.IIMCService;
import com.huerta.actividad.app.util.Validator;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/imc")
public class IMCServlet extends HttpServlet {

    private IIMCService service = new IMCServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // mostrar formulario solo si hay sesión
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        req.getRequestDispatcher("/imcForm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        Persona persona = (Persona) session.getAttribute("usuario");
        String pesoS = req.getParameter("peso");
        String error = null;
        double peso = 0;
        try {
            peso = Double.parseDouble(pesoS);
        } catch (NumberFormatException e) {
            error = "Peso inválido.";
        }
        if (error == null && !Validator.validarPeso(peso)) {
            error = "Peso debe ser positivo.";
        }
        if (error != null) {
            req.setAttribute("error", error);
            req.getRequestDispatcher("/imcForm.jsp").forward(req, resp);
            return;
        }

        IMCDTO dto = service.calcularIMC(persona, peso);
        req.setAttribute("resultado", dto);
        // mostrar resultado y opción para ver historial
        req.getRequestDispatcher("/resultado.jsp").forward(req, resp);
    }
}
