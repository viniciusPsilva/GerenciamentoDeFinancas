package br.com.viniciuspsilva.GerenciamentoDeFinancas.exception.handler;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.exception.dto.DefaultErrorDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.exception.gasto.GastoNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GastoExceptionHandler {


    @ExceptionHandler(value = GastoNotFoundException.class)
    public ResponseEntity<DefaultErrorDto> handleGastoNotFound(GastoNotFoundException ex){

        DefaultErrorDto error = new DefaultErrorDto();
        error.setMensagem(ex.getMessage());

        return ResponseEntity.badRequest().body(error);

    }

}
