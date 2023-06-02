package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.mappers;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.CategoriaDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.domain.Categoria;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.CategoriaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoriaMapper {

    static final CategoriaMapper INSTANCE = Mappers.getMapper(CategoriaMapper.class);

    Categoria mapFromCategoriaDto(CategoriaDto source);
    Categoria mapFromCategoriaEntity(CategoriaEntity source);
    CategoriaDto mapToDto(Categoria source);
    CategoriaEntity mapToEntity(Categoria source);


}
