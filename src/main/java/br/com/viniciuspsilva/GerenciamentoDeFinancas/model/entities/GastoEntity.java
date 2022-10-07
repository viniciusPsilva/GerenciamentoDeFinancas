package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enums.MesReferencia;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enums.Prioridade;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enums.StatusGasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enums.Tipo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class GastoEntity {

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
    private Integer totalParcelas;

    @Column(nullable = true)
    private Integer parcelaAtual;

    @Column(updatable = false)
    private LocalDate dataCriacao;

    @ManyToOne(targetEntity = PlanejamentoMensalDeGastoEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id_plano", name = "id_plano")
    private PlanejamentoMensalDeGastoEntity planoDeGasto;

    @ManyToOne(targetEntity = CategoriaEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "id_categoria", name = "id_categoria")
    private CategoriaEntity categoria;

}
