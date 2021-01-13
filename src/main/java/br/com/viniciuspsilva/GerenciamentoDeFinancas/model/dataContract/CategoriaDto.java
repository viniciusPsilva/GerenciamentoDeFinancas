package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.Gasto;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Builder
@Data
public class CategoriaDto {
    @Size(min = 3, max = 120)
    @NotNull
    private String nome;

    @Size(min = 3, max = 255)
    private String descricao;

    private List<Gasto> gastos;

    private LocalDate dataCriacao;
}
