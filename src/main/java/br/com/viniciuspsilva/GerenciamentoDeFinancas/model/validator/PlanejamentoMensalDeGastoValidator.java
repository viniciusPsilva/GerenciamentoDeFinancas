package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.validator;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.exception.ValidacaoFisicaException;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.exception.dto.RequestValidationErrorDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.PlanoDeGastoDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class PlanejamentoMensalDeGastoValidator {

    private List<RequestValidationErrorDto> errors = new ArrayList<>();

    public boolean validate(PlanoDeGastoDto planejamentoMensalDeGastoDto){

        validateDataAtualizacao(planejamentoMensalDeGastoDto);

        if (!errors.isEmpty()){
            throw new ValidacaoFisicaException("Erro de validação física", errors);
        }

        return true;
    }

    private void validateDataAtualizacao(PlanoDeGastoDto planejamentoMensalDeGastoDto) {

        if (Objects.nonNull(planejamentoMensalDeGastoDto.getDataAtualizacao())){

            RequestValidationErrorDto errorDto = RequestValidationErrorDto.builder()
                    .field("data_atualizacao")
                    .message("O campo data_atualizacao não deve ser informado.")
                    .build();

            errors.add(errorDto);
        }

    }


}
