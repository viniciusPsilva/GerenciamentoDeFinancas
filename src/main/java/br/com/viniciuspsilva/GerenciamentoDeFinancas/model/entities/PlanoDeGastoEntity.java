package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enums.StatusPlano;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
@Setter
@Getter
@Entity(name = "tbl01_plano_de_gasto")
public class PlanoDeGastoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_plano")
    private Integer id;

    @Column(nullable = false, length = 60, unique = true)
    private String titulo;

    @Column(nullable = false)
    private String descricao;

    @Column
    private BigDecimal valorPlanejado;

    @Column(nullable = false)
    private BigDecimal totalGasto;

    @Enumerated(value = EnumType.STRING)
    private StatusPlano status = StatusPlano.OK;

    @Column(updatable = false)
    private LocalDate dataCriacao = LocalDate.now();

    @Column
    private LocalDate dataAtualizacao = LocalDate.now();


    @Column(updatable = false)
    private Integer idUsuario;

}
