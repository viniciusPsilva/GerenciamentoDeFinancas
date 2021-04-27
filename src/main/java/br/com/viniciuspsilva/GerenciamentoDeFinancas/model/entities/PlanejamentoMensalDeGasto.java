package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enumerations.MesReferencia;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enumerations.StatusPlano;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
@Setter
@Getter
@Entity
public class PlanejamentoMensalDeGasto {

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
    private MesReferencia mesReferencia;

    @Column(updatable = false)
    private LocalDate dataCriacao = LocalDate.now();

    @Column(updatable = false)
    private Integer id_Usuario;

}
