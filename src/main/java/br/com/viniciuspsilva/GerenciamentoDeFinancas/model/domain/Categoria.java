package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Categoria {
    private Integer id;
    private String nome;
    private String descricao;
}
