package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.mapper;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.CategoriaDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.Categoria;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.mappers.CategoriaMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class CategoriaMapperTest {

    @Test
    public void mapToCategoria(){
        CategoriaDto categoriaDto = new CategoriaDto();
        categoriaDto.setNome("Alimentação");
        categoriaDto.setDescricao("Categoria destinada para gastos com Alimentação");
        Categoria target = CategoriaMapper.INSTANCE.mapFromCategoriaDto(categoriaDto);

        Assertions.assertEquals(categoriaDto.getNome(), target.getNome());
        Assertions.assertEquals(categoriaDto.getDescricao(), target.getDescricao());
    }

    @Test
    public void mapToCategoriaDto(){
        Categoria source = new Categoria();
        source.setDescricao("Categoria destinada para gastos com Alimentação");
        source.setNome("Alimentação");
        source.setDataCriacao(LocalDate.of(2021, 2, 13));

        CategoriaDto target = CategoriaMapper.INSTANCE.mapFromCategoria(source);
        Assertions.assertEquals(source.getNome(), target.getNome());
        Assertions.assertEquals(source.getDescricao(), target.getDescricao());
    }
}
