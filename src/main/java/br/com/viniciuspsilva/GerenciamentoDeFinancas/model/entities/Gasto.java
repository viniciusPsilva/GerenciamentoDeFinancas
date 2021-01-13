package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enumerations.Prioridade;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enumerations.StatusGasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enumerations.Tipo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Gasto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gasto")
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(nullable = false)
    private LocalDate dataReferencia;

    @Column(nullable = false)
    private LocalDate dataVencimento;

    @Column(nullable = false)
    private Tipo tipo;

    @Column(nullable = false)
    private StatusGasto status;

    @Column(nullable = false)
    private Prioridade prioridade;

    @Column(nullable = true)
    private int totalParcelas;

    @Column(nullable = true)
    private int parcelaAtual;

    @Column(updatable = false)
    private LocalDate dataCriacao = LocalDate.now();

    @ManyToOne(targetEntity = PlanoDeGasto.class, fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id_plano")
    private PlanoDeGasto planoDeGasto;

    @ManyToOne(targetEntity = Categoria.class, fetch = FetchType.EAGER)
    private Categoria categoria;

}
