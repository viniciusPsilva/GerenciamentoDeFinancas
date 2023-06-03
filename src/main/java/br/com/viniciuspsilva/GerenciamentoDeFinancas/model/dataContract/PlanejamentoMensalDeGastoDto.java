package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enums.MesReferencia;
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
public class PlanejamentoMensalDeGastoDto {

    @Size(min = 3, max = 120)
    private String titulo;

    @Size(min = 3, max = 255)
    private String descricao;

    @JsonProperty("valor_planejado")
    @Digits(integer = 10, fraction = 2)
    private BigDecimal valorPlanejado;

    @JsonProperty("total_gasto")
    @Digits(integer = 10, fraction = 2)
    private BigDecimal totalGasto;

    @NotNull
    private StatusPlano status;

    @JsonProperty("mes_referencia")
    @NotNull
    private String mesReferencia;

    @JsonProperty("data_criacao")
    private LocalDate dataCriacao = LocalDate.now();
}
