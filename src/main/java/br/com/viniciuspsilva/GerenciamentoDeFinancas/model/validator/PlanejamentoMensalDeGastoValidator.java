package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.validator;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.exception.ValidacaoFisicaException;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.exception.dto.RequestValidationErrorDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.request.PlanoDeGastoRequestDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class PlanejamentoMensalDeGastoValidator {

    private List<RequestValidationErrorDto> errors = new ArrayList<>();

    public boolean validate(PlanoDeGastoRequestDto planejamentoMensalDeGastoDto){

        if (!errors.isEmpty()){
            throw new ValidacaoFisicaException("Erro de validação física", errors);
        }

        return true;
    }

}
