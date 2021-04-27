package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enumerations.MesReferencia;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enumerations.Prioridade;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enumerations.StatusGasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enumerations.Tipo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
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
    private MesReferencia mesReferencia;

    @Column
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
    private LocalDate dataCriacao;

    @ManyToOne(targetEntity = PlanejamentoMensalDeGasto.class, fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id_plano")
    private PlanejamentoMensalDeGasto planoDeGasto;

    @ManyToOne(targetEntity = Categoria.class, fetch = FetchType.EAGER)
    private Categoria categoria;

}
