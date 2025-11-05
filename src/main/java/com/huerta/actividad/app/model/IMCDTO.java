package com.huerta.actividad.app.model;


public class IMCDTO {
    private double peso;
    private double imc;
    private String categoria;
    private String fechaMedicion; // iso string o formato legible

    // getters/setters y constructores
    public IMCDTO(){}
    public IMCDTO(double peso, double imc, String categoria, String fechaMedicion){
        this.peso = peso; this.imc = imc; this.categoria = categoria; this.fechaMedicion = fechaMedicion;
    }

    public double getPeso(){ return peso; }
    public void setPeso(double peso){ this.peso = peso; }
    public double getImc(){ return imc; }
    public void setImc(double imc){ this.imc = imc; }
    public String getCategoria(){ return categoria; }
    public void setCategoria(String categoria){ this.categoria = categoria; }
    public String getFechaMedicion(){ return fechaMedicion; }
    public void setFechaMedicion(String fechaMedicion){ this.fechaMedicion = fechaMedicion; }
}