package br.com.viniciuspsilva.GerenciamentoDeFinancas.exception.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestValidationErrorDto {
    private String field;
    private String message;
}
