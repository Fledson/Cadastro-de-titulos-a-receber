package com.zihenx.cobranca.model;

/**
 * Classe enun => escolha entre status do titulo
 */
public enum StatusTitulo {
    PENDENTE("Pendente"),
    RECEBIDO("Recebido");

    private String descricao;

    StatusTitulo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
