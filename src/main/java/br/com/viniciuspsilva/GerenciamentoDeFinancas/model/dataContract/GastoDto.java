package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.Categoria;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.PlanoDeGasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enumerations.Prioridade;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enumerations.StatusGasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enumerations.Tipo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Data
public class GastoDto {

    @JsonIgnoreProperties
    private Integer id;

    @Size(min = 3, max = 120)
    @NotNull
    private String nome;

    @Size(min = 3, max = 255)
    @NotNull
    private String descricao;

    @Digits(integer = 10, fraction = 2)
    @NotNull
    private BigDecimal valor;

    @NotNull
    private LocalDate dataReferencia;

    @Future
    private LocalDate dataVencimento;

    @NotNull
    private Tipo tipo;

    @NotNull
    private StatusGasto status;

    @NotNull
    private Prioridade prioridade;

    @Digits(integer = 2, fraction = 0)
    private int totalParcelas;

    @Digits(integer = 2, fraction = 0)
    private int parcelaAtual;

    private LocalDate dataCriacao;

    @JsonIgnoreProperties
    private Integer idPlanoDeGasto;

    @JsonIgnoreProperties
    private Integer idCategoria;
}
