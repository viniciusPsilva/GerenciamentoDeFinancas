package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.Categoria;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.PlanoDeGasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enumerations.Prioridade;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enumerations.StatusGasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enumerations.Tipo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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

@Getter
@Setter
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

    @JsonProperty(value = "data_referencia")
    @NotNull
    private LocalDate dataReferencia;

    @JsonProperty(value = "data_vencimento")
    @Future
    private LocalDate dataVencimento;

    @NotNull
    private Tipo tipo;

    @NotNull
    private StatusGasto status;

    @NotNull
    private Prioridade prioridade;

    @JsonProperty(value = "total_parcelas")
    @Digits(integer = 2, fraction = 0)
    private int totalParcelas;

    @JsonProperty(value = "parcela_atual")
    @Digits(integer = 2, fraction = 0)
    private int parcelaAtual;

    private LocalDate dataCriacao;

    @JsonProperty(value = "id_plano")
    @JsonIgnoreProperties
    private Integer idPlanoDeGasto;

    @JsonProperty(value = "id_categoria")
    @JsonIgnoreProperties
    private Integer idCategoria;
}
