package br.com.viniciuspsilva.GerenciamentoDeFinancas.exception;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.exception.dto.RequestValidationErrorDto;
import lombok.Getter;

import java.util.List;

@Getter
public class ValidacaoFisicaException extends RuntimeException{

    private List<RequestValidationErrorDto> errors;
    public ValidacaoFisicaException(String message) {
        super(message);
    }

    public ValidacaoFisicaException(String message, List<RequestValidationErrorDto> errors) {
        super(message);
        this.errors = errors;
    }
}
