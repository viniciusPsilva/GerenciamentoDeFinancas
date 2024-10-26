package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enums.StatusPlano;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class PlanoDeGastoFormUpdateDto {

    @Size(min = 3, max = 255)
    private String descricao;

    @JsonProperty("valor_planejado")
    @Digits(integer = 10, fraction = 2)
    private BigDecimal valorPlanejado;
}
