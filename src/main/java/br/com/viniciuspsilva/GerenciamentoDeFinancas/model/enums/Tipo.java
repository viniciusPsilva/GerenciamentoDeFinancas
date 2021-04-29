package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enums;

import lombok.Getter;

@Getter
public enum Tipo {
    GASTO("gasto"),
    FREQUENTE("frequente"),
    PARCELADO("percelado");

    private String descricao;

    Tipo(String descricao){
        this.descricao = descricao;
    }

}
