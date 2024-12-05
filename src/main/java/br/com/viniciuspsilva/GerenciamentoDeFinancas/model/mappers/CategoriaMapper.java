package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.mappers;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.CategoriaDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.request.CategoriaFormUpdateDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.domain.Categoria;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.CategoriaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoriaMapper {

    static final CategoriaMapper INSTANCE = Mappers.getMapper(CategoriaMapper.class);

    Categoria mapFromDto(CategoriaDto source);
    Categoria mapFromDto(CategoriaFormUpdateDto source);
    Categoria mapFromEntity(CategoriaEntity source);
    Iterable<Categoria> mapFromEntity(Iterable<CategoriaEntity> sources);
    CategoriaDto mapToDto(Categoria source);
    Iterable<CategoriaDto> mapToDto(Iterable<Categoria> sources);
    CategoriaEntity mapToEntity(Categoria source);


}
