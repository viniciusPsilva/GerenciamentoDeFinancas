package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@Data
public class UsuarioDto {

    @Size(min = 1, max = 120)
    @NotNull
    private String nome;

    @Size(min = 1, max = 120)
    @NotNull
    @JsonProperty("sobre_nome")
    private String sobreNome;

    @Size(min = 1, max = 120)
    private String apelido;

    @Email
    @NotNull
    private String email;
}
