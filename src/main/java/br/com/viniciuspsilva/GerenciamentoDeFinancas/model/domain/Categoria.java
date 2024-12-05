package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDate;

@Getter
@Setter
public class Categoria {
    private Integer id;
    private String nome;
    private String descricao;
    private LocalDate dataAtualizacao;

    //TODO remover usuario padrão após a implementação da autenticação.
    private String idUsuario = "cb98326b-d84b-4f98-a2ad-388a87736ae7";
}
