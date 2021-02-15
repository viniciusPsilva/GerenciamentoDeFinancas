package br.com.viniciuspsilva.GerenciamentoDeFinancas.exception.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDto {
    private String field;
    private String message;
}
