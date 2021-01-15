package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract;

import lombok.Builder;
import lombok.Data;

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

    private LocalDate dataCriacao;
}
