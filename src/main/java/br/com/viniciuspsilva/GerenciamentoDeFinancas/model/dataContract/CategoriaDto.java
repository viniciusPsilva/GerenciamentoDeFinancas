package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CategoriaDto {

    private Integer id;

    @Size(min = 3, max = 120)
    @NotNull
    private String nome;

    @Size(min = 3, max = 255)
    @NotNull
    private String descricao;
}
