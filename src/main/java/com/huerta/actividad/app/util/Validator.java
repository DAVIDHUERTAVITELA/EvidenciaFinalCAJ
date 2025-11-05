package com.huerta.actividad.app.util;

import com.huerta.actividad.app.model.Persona;

public class Validator {

    public static boolean validarEstatura(double estatura) {
        return estatura >= 1.0 && estatura <= 2.5;
    }

    public static boolean validarEdad(int edad) {
        return edad >= 15;
    }

    public static boolean validarPeso(double peso) {
        return peso > 0;
    }

    public static boolean validarRegistro(Persona p) {
        return p != null && validarEdad(p.getEdad()) && validarEstatura(p.getEstatura());
    }
}