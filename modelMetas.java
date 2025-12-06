package com.gestaometas.model;

import java.time.LocalDate;

public class Meta {
    private Long id;
    private String titulo;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private int progresso;
    private boolean concluida;

    public Meta() {
        this.dataInicio = LocalDate.now();
        this.progresso = 0;
        this.concluida = false;
    }

    public Meta(String titulo, String descricao, LocalDate dataFim) {
        this();
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataFim = dataFim;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    
    public LocalDate getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }
    
    public LocalDate getDataFim() { return dataFim; }
    public void setDataFim(LocalDate dataFim) { this.dataFim = dataFim; }
    
    public int getProgresso() { return progresso; }
    public void setProgresso(int progresso) { 
        this.progresso = progresso;
        if (progresso >= 100) {
            this.concluida = true;
        }
    }
    
    public boolean isConcluida() { return concluida; }
    public void setConcluida(boolean concluida) { this.concluida = concluida; }

    public boolean estaVencida() {
        return LocalDate.now().isAfter(dataFim) && !concluida;
    }
    
    public long diasRestantes() {
        return Math.max(0, LocalDate.now().until(dataFim).getDays());
    }
}
