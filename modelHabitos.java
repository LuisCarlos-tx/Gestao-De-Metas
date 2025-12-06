package com.gestaometas.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Habito {
    private Long id;
    private String nome;
    private String descricao;
    private Map<LocalDate, Boolean> registros;
    private LocalDate dataCriacao;

    public Habito() {
        this.registros = new HashMap<>();
        this.dataCriacao = LocalDate.now();
    }

    public Habito(String nome, String descricao) {
        this();
        this.nome = nome;
        this.descricao = descricao;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    
    public Map<LocalDate, Boolean> getRegistros() { return registros; }
    public void setRegistros(Map<LocalDate, Boolean> registros) { this.registros = registros; }
    
    public LocalDate getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDate dataCriacao) { this.dataCriacao = dataCriacao; }

    // Métodos de negócio
    public void registrarDia(LocalDate data, boolean realizado) {
        this.registros.put(data, realizado);
    }
    
    public double calcularTaxaSucesso() {
        if (registros.isEmpty()) return 0.0;
        long realizados = registros.values().stream().filter(b -> b).count();
        return (double) realizados / registros.size() * 100;
    }
    
    public int calcularStreak() {
        int streak = 0;
        LocalDate hoje = LocalDate.now();
        
        while (registros.containsKey(hoje) && registros.get(hoje)) {
            streak++;
            hoje = hoje.minusDays(1);
        }
        
        return streak;
    }
}
