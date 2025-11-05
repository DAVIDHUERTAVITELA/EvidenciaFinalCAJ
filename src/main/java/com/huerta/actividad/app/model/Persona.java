package com.huerta.actividad.app.model;

public class Persona {
    private int id;
    private String nombreCompleto;
    private String nombreUsuario;
    private int edad;
    private String sexo;
    private double estatura; // metros

    // getters y setters
    // constructor vacío y con parámetros
    public Persona(){}

    public Persona(int id, String nombreCompleto, String nombreUsuario, int edad, String sexo, double estatura){
        this.id = id; this.nombreCompleto = nombreCompleto; this.nombreUsuario = nombreUsuario;
        this.edad = edad; this.sexo = sexo; this.estatura = estatura;
    }

    // getters/setters...
    public int getId(){ return id; }
    public void setId(int id){ this.id = id; }
    public String getNombreCompleto(){ return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto){ this.nombreCompleto = nombreCompleto; }
    public String getNombreUsuario(){ return nombreUsuario; }
    public void setNombreUsuario(String nombreUsuario){ this.nombreUsuario = nombreUsuario; }
    public int getEdad(){ return edad; }
    public void setEdad(int edad){ this.edad = edad; }
    public String getSexo(){ return sexo; }
    public void setSexo(String sexo){ this.sexo = sexo; }
    public double getEstatura(){ return estatura; }
    public void setEstatura(double estatura){ this.estatura = estatura; }
}