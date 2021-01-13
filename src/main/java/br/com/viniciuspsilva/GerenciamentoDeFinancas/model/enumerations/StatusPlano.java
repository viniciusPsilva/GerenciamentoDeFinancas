package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enumerations;

import lombok.Getter;

@Getter
public enum StatusPlano {

    OK("ok"),
    ACIMA_DO_PLANEJADO("acima do planejado");

    private String descricao;

    StatusPlano(String descricao) {
        this.descricao = descricao;
    }
}
