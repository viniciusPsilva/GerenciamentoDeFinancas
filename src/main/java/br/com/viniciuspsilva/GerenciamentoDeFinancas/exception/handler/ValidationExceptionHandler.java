package br.com.viniciuspsilva.GerenciamentoDeFinancas.exception.handler;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.exception.ValidacaoFisicaException;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.exception.dto.RequestValidationErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ValidationExceptionHandler {
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationsErrors(MethodArgumentNotValidException ex){

        List<RequestValidationErrorDto> errors = new ArrayList<>();
        List<FieldError> fieldErrors = ex.getFieldErrors();

        fieldErrors.forEach((error)->{
            var erro = new RequestValidationErrorDto();
            erro.setField(error.getField());
            erro.setMessage(error.getDefaultMessage());

            errors.add(erro);
        });


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(List.of(errors));
    }

    @ExceptionHandler(value = ValidacaoFisicaException.class)
    public ResponseEntity<Object> handleValidationsErrors(ValidacaoFisicaException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getErrors());
    }


}
