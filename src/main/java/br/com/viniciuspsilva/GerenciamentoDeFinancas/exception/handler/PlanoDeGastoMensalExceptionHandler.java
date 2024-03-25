package br.com.viniciuspsilva.GerenciamentoDeFinancas.exception.handler;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.exception.dto.DefaultErrorDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.exception.planoDeGasto.PlanoDeGastoException;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.exception.planoDeGasto.PlanoDeGastoNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class PlanoDeGastoMensalExceptionHandler {


    @ExceptionHandler(value = PlanoDeGastoNotFoundException.class)
    public ResponseEntity<Object> handlePlanoDeGastosMensalErrors(PlanoDeGastoNotFoundException ex){

        DefaultErrorDto error = new DefaultErrorDto();
        error.setMensagem(ex.getMessage());

        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(value = PlanoDeGastoException.class)
    public ResponseEntity<Object> handlePlanoDeGastosMensalErrors(PlanoDeGastoException ex){

        DefaultErrorDto error = new DefaultErrorDto();
        error.setMensagem(ex.getMessage());

        return ResponseEntity.badRequest().body(error);
    }
}
