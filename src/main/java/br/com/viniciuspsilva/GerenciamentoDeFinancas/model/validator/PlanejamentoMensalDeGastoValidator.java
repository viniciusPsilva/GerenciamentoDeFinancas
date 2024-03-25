package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.validator;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.exception.ValidacaoFisicaException;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.exception.dto.RequestValidationErrorDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.exception.planoDeGasto.PlanoDeGastoException;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.PlanejamentoMensalDeGastoDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enums.MesReferencia;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlanejamentoMensalDeGastoValidator {

    private List<RequestValidationErrorDto> errors = new ArrayList<>();

    public boolean validate(PlanejamentoMensalDeGastoDto planejamentoMensalDeGastoDto){
        validateMesReferencia(planejamentoMensalDeGastoDto.getMesReferencia());


        if (!errors.isEmpty()){
            throw new ValidacaoFisicaException("Erro de validação física", errors);
        }

        return true;
    }

    private void validateMesReferencia(String mesReferencia) {
        if (!MesReferencia.exists(mesReferencia)){
            errors.add(RequestValidationErrorDto.builder()
                    .field("mes_referencia")
                    .message("O campo mes_referencia está invalido.")
                    .build());
        }
    }

}
