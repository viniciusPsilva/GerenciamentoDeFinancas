package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.domain;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enums.StatusPlano;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class PlanoDeGasto {
    private Integer id;
    private String titulo;
    private String descricao;
    private BigDecimal valorPlanejado;
    private BigDecimal totalGasto;
    private StatusPlano status;
    private LocalDate dataCriacao;
}
