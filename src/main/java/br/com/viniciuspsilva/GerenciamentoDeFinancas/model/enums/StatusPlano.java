package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enums;

import lombok.Getter;

@Getter
public enum StatusPlano {

    OK("ok"),
    ACIMA_DO_PLANEJADO("acima do planejado"),
    NAO_PLANEJADO("não planejado");

    private String descricao;

    StatusPlano(String descricao) {
        this.descricao = descricao;
    }
}
