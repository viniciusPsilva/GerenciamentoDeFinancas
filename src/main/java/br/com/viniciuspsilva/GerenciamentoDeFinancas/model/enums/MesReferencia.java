package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enums;

import lombok.Getter;

@Getter
public enum MesReferencia {

    JANEIRO("1", "janeiro"),
    FEVEREIRO("2", "fevereiro"),
    MARCO("3", "março"),
    ABRIL("4", "abril"),
    MAIO("5", "maio"),
    JUNHO("6", "junho"),
    JULHO("7", "julho"),
    AGOSTO("8", "agosto"),
    SETEMBRO("9", "setembro"),
    OUTUBRO("10", "outubro"),
    NOVEMBRO("11", "novembro"),
    DEZEMBRO("12", "dezembro");

    private final String codigo;
    private final String nome;

    MesReferencia(String codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }
}
