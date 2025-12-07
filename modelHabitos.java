package com.gestaometas.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Habito {
    private Long id;
    private String nome;
    private String descricao;
    private Map<LocalDate, Boolean> registros;
    
    public Habito() {
        this.registros = new HashMap<>();
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    
    public Map<LocalDate, Boolean> getRegistros() { return registros; }
    
    public void registrarDia(LocalDate data, boolean realizado) {
        this.registros.put(data, realizado);
    }
}
