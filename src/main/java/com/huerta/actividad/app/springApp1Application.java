package com.huerta.actividad.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan // 🔹 Permite que Spring escanee y registre automáticamente los servlets con @WebServlet
public class springApp1Application {

    public static void main(String[] args) {
        SpringApplication.run(springApp1Application.class, args);
        System.out.println("🚀 Servidor iniciado en http://localhost:8080");
    }
}