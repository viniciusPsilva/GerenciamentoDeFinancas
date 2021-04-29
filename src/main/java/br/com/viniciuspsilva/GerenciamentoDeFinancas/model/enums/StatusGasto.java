package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enums;

import lombok.Getter;

@Getter
public enum StatusGasto {

    EM_ABERTO("em aberto"),
    PAGO("pago"),
    VENCIDO("vencido");

    private String descricao;

    StatusGasto(String descricao) {
        this.descricao = descricao;
    }
}
