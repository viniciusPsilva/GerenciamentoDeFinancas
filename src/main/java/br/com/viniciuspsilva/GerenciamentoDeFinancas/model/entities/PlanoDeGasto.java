package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enumerations.StatusPlano;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
public class PlanoDeGasto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_plano")
    private Integer Id;

    @Column(nullable = false, length = 60, unique = true)
    private String titulo;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private BigDecimal valorPlanejado;

    @Column(nullable = false)
    private BigDecimal totalGasto;

    @Enumerated(value = EnumType.STRING)
    private StatusPlano status = StatusPlano.OK;

    @Column(nullable = false)
    private LocalDate dataReferencia;

    @Column(updatable = false)
    private LocalDate dataCriacao = LocalDate.now();

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Gasto> gastos;

}
