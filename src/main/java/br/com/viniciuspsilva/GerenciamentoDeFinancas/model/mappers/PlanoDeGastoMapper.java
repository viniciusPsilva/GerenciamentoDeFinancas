package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.mappers;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.PlanoDeGastoDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.PlanoDeGasto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlanoDeGastoMapper {

    PlanoDeGastoMapper INSTANCE = Mappers.getMapper(PlanoDeGastoMapper.class);

    PlanoDeGastoDto mapFromPlanoDeGasto(PlanoDeGasto source);

    PlanoDeGasto mapFromPladoDeGastoDto(PlanoDeGastoDto source);

}
