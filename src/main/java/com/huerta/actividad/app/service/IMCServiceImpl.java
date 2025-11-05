package com.huerta.actividad.app.service;

import com.huerta.actividad.app.dao.MedicionDAO;
import com.huerta.actividad.app.model.IMCDTO;
import com.huerta.actividad.app.model.Persona;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IMCServiceImpl implements IIMCService {

    private MedicionDAO medicionDAO = new MedicionDAO();

    @Override
    public IMCDTO calcularIMC(Persona persona, double peso) {
        double estatura = persona.getEstatura();
        double imc = peso / (estatura * estatura);
        double imcRounded = Math.round(imc * 100.0) / 100.0;
        String categoria = obtenerCategoria(imcRounded);
        String fecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        IMCDTO dto = new IMCDTO(peso, imcRounded, categoria, fecha);

        // persistir en BD (si ocurre excepción, se propaga/loguea)
        try {
            medicionDAO.guardarMedicion(persona.getId(), peso, imcRounded, categoria);
        } catch (SQLException e) {
            // Registrar/log o lanzar runtime
            e.printStackTrace();
        }

        return dto;
    }

    private String obtenerCategoria(double imc) {
        if (imc < 18.5) return "Bajo peso";
        if (imc < 25.0) return "Peso normal";
        if (imc < 30.0) return "Sobrepeso";
        return "Obesidad";
    }
}