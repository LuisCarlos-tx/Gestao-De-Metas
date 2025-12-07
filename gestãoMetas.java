package com.gestaometas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestaoMetasApplication {
    public static void main(String[] args) {
        SpringApplication.run(GestaoMetasApplication.class, args);
        System.out.println("✅ API Rodando: http://localhost:8080");
    }
}
