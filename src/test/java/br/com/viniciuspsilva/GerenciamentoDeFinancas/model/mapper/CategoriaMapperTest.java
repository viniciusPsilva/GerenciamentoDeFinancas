package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.mapper;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.CategoriaDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.domain.Categoria;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.CategoriaEntity;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.mappers.CategoriaMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class CategoriaMapperTest {
    @BeforeEach
    public void beforeEach(){
        FixtureFactoryLoader.loadTemplates("br.com.viniciuspsilva.GerenciamentoDeFinancas.template");
    }

    @Test
    public void mapToCategoriaFromCategoriaDto(){
        CategoriaDto categoriaDto = Fixture.from(CategoriaDto.class).gimme("valid");

        Categoria target = CategoriaMapper.INSTANCE.mapFromCategoriaDto(categoriaDto);

        Assertions.assertEquals(categoriaDto.getNome(), target.getNome());
        Assertions.assertEquals(categoriaDto.getDescricao(), target.getDescricao());
    }

    @Test
    public void mapToCategoriaDtoFromCategoria(){
        Categoria categoria = Fixture.from(Categoria.class).gimme("valid");
        CategoriaDto target = CategoriaMapper.INSTANCE.mapToDto(categoria);

        Assertions.assertEquals(categoria.getNome(), target.getNome());
        Assertions.assertEquals(categoria.getDescricao(), target.getDescricao());
    }

    @Test
    public void mapToCategoriaFromCategoriaEntity(){
        CategoriaEntity source = Fixture.from(CategoriaEntity.class).gimme("valid");
        source.setDataCriacao(LocalDate.of(2021, 2, 13));

        Categoria target = CategoriaMapper.INSTANCE.mapFromCategoriaEntity(source);
        Assertions.assertEquals(source.getNome(), target.getNome());
        Assertions.assertEquals(source.getDescricao(), target.getDescricao());
    }

    @Test
    public void mapToCategoriaEntityFromCategoria(){
        Categoria source = Fixture.from(Categoria.class).gimme("valid");

        CategoriaEntity target = CategoriaMapper.INSTANCE.mapToEntity(source);
        Assertions.assertEquals(source.getNome(), target.getNome());
        Assertions.assertEquals(source.getDescricao(), target.getDescricao());
        Assertions.assertEquals(LocalDate.now(), target.getDataCriacao());
    }
}
