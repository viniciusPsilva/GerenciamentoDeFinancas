package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.domain;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enums.MesReferencia;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enums.Prioridade;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enums.StatusGasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enums.Tipo;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class Gasto {
    private Integer id;
    private String nome;
    private String descricao;
    private BigDecimal valor;
    private MesReferencia mesReferencia;
    private LocalDate dataVencimento;
    private Tipo tipo;
    private StatusGasto status;
    private Prioridade prioridade;
    private int totalParcelas = 0;
    private int parcelaAtual = 0;
    private LocalDate dataCriacao = LocalDate.now();
    private Integer idPlanoDeGasto;
    private Integer idCategoria;
}
