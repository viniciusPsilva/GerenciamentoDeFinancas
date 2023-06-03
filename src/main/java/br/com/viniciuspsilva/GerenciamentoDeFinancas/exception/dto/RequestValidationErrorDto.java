package br.com.viniciuspsilva.GerenciamentoDeFinancas.exception.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestValidationErrorDto {
    private String field;
    private String message;
}
