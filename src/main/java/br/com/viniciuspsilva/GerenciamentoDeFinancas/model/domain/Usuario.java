package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Usuario {
    //TODO remover usuario padrão após a implementação da autenticação.
    private String id = "cb98326b-d84b-4f98-a2ad-388a87736ae7";;
    private String nome;
    private String sobreNome;
    private String apelido;
    private String email;
    private LocalDate dataCriacao;
}
