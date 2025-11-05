package com.huerta.actividad.app.service;

import com.huerta.actividad.app.model.IMCDTO;
import com.huerta.actividad.app.model.Persona;

public interface IIMCService {
    IMCDTO calcularIMC(Persona persona, double peso);
}