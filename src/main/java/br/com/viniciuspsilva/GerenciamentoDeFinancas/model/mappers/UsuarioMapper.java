package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.mappers;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.UsuarioDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.domain.Usuario;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.UsuarioEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import javax.swing.text.html.parser.Entity;
import java.time.LocalDate;
import java.util.UUID;

@Mapper
public abstract class UsuarioMapper {
    public static UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    public abstract Usuario mapFromDto(UsuarioDto source);

    public abstract UsuarioDto mapToDto(Usuario source);

    public abstract UsuarioEntity mapToEntity(Usuario source);

    @BeforeMapping
    public void beforeMapFromDto(final UsuarioDto source, @MappingTarget final Usuario target) {
        target.setDataCriacao(LocalDate.now());
        target.setId(UUID.randomUUID().toString());
    }

    @BeforeMapping
    public void beforeMapToEntity(final Usuario source, @MappingTarget final UsuarioEntity target) {
        target.setSenha(UUID.randomUUID().toString());
    }

}
