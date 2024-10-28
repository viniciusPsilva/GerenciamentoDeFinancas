package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CategoriaFormUpdateDto {

    @Size(min = 3, max = 255)
    @NotNull
    private String descricao;
}
