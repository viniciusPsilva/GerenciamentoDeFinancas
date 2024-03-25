package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enums.MesReferencia;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enums.StatusPlano;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
@Setter
@Getter
@Entity(name = "planejamento_mensal_de_gasto")
public class PlanejamentoMensalDeGastoEntity {

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

    @Column(nullable = false)
    private MesReferencia mesReferencia;

    @Column(updatable = false)
    private LocalDate dataCriacao = LocalDate.now();

    @Column(updatable = false)
    private Integer idUsuario;

}
