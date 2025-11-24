package com.gestaohabitos.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Habit {
    private Long id;
    private String nome;
    private String descricao;
    private Map<LocalDate, Boolean> registros;
    private LocalDate dataCriacao;

    public Habit() {
        this.registros = new HashMap<>();
        this.dataCriacao = LocalDate.now();
    }

    public Habit(Long id, String nome, String descricao) {
        this();
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

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

    public void registrarDia(LocalDate data, boolean realizado) {
        this.registros.put(data, realizado);
    }
}
