package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.mappers;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.CategoriaDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.Categoria;

public class CategoriaMapper {
    public static CategoriaDto toDto(Categoria categoria){
        return CategoriaDto.builder()
                .nome(categoria.getNome())
                .descricao(categoria.getDescricao())
                .dataCriacao(categoria.getDataCriacao())
                .build();
    }

    public static Categoria toEntity(CategoriaDto categoriaDto){
        return Categoria.builder()
                .nome(categoriaDto.getNome())
                .descricao(categoriaDto.getDescricao())
                .build();
    }

}
