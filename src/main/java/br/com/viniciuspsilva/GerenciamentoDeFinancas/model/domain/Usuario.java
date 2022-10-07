package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Getter
@Setter
public class Usuario {
    private String nome;
    private String sobreNome;
    private String apelido;
    private String email;
    private LocalDate dataCriacao;
}
