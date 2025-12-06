package com.gestaometas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestaoMetasApplication {
    public static void main(String[] args) {
        SpringApplication.run(GestaoMetasApplication.class, args);
        System.out.println("========================================");
        System.out.println("🚀 API de Gestão de Metas INICIADA!");
        System.out.println("📍 URL: http://localhost:8080");
        System.out.println("📚 Swagger: http://localhost:8080/swagger-ui.html");
        System.out.println("👨‍💻 Aluno: Luis Carlos Tavares Xavier");
        System.out.println("🎓 Matrícula: 20240029664");
        System.out.println("========================================");
    }
}
