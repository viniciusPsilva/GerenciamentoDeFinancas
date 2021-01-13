package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.UUID;

@Builder
@Data
public class UsuarioDto {

    @Size(min = 1, max = 120)
    @NotNull
    private String nome;

    @Size(min = 1, max = 120)
    @NotNull
    private String sobreNome;

    @Size(min = 1, max = 120)
    private String apelido;

    @Email
    @NotNull
    private String email;

    private LocalDate dataCriacao;
}
