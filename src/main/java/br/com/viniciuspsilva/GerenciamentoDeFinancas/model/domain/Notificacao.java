package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Getter
@Setter
public class Notificacao {
    private String titulo;
    private String mensagem;
    private LocalDate dataCriacao;
    private Usuario usuarioEntity;
}
