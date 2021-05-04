package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enums.MesReferencia;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enums.Prioridade;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enums.StatusGasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enums.Tipo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class GastoUpdatedDto {

    @Size(min = 3, max = 120)
    private String nome;

    @Size(min = 3, max = 255)
    private String descricao;

    @Digits(integer = 10, fraction = 2)
    private BigDecimal valor;

    @JsonProperty(value = "mes_referencia")
    private MesReferencia mesReferencia;

    @JsonProperty(value = "data_vencimento")
    @Future
    private LocalDate dataVencimento;

    private Tipo tipo;

    private StatusGasto status;

    private Prioridade prioridade;

    @JsonProperty(value = "total_parcelas")
    @Digits(integer = 2, fraction = 0)
    private int totalParcelas = 0;

    @JsonProperty(value = "parcela_atual")
    @Digits(integer = 2, fraction = 0)
    private int parcelaAtual = 0;

    @JsonProperty(value = "id_plano")
    private Integer idPlanoDeGasto;

    @JsonProperty(value = "id_categoria")
    private Integer idCategoria;
}
