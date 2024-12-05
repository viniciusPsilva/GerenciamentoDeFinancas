package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enums;

import lombok.Getter;

@Getter
public enum Prioridade {
    ALTA("alta"),
    MEDIA("media"),
    BAIXA("baixa"),
    DESPRIORIZADO("despriorizado");

    private String descricao;

    Prioridade(String descricao) {
        this.descricao = descricao;
    }
}
