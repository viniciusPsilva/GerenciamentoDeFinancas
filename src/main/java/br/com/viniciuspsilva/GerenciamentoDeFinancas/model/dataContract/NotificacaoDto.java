package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.UsuarioEntity;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Builder
@Data
public class NotificacaoDto {

    @Size(min = 3, max = 120)
    @NotNull
    private String titulo;

    @Size(min = 3, max = 255)
    @NotNull
    private String mensagem;

    private LocalDate dataCriacao;

    private UsuarioEntity usuarioEntity;
}
