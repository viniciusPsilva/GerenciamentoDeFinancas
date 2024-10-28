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
    private Integer idUsuario = 1;
}
