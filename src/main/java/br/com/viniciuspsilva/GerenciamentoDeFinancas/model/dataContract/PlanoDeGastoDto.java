package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enumerations.StatusPlano;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class PlanoDeGastoDto {

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
    @Future
    private LocalDate dataReferencia;

    private LocalDate dataCriacao;
}
