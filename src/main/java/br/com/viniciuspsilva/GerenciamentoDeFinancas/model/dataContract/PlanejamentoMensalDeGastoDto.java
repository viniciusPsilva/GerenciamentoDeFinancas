package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enums.MesReferencia;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enums.StatusPlano;
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

    @Digits(integer = 10, fraction = 2)
    private BigDecimal valorPlanejado;

    @Digits(integer = 10, fraction = 2)
    private BigDecimal totalGasto;

    @NotNull
    private StatusPlano status;

    @NotNull
    private MesReferencia mesReferencia;

    private LocalDate dataCriacao;
}
