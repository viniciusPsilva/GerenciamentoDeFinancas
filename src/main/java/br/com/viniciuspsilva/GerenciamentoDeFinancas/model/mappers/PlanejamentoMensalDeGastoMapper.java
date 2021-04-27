package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.mappers;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.PlanejamentoMensalDeGastoDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.PlanejamentoMensalDeGasto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlanejamentoMensalDeGastoMapper {

    PlanejamentoMensalDeGastoMapper INSTANCE = Mappers.getMapper(PlanejamentoMensalDeGastoMapper.class);

    PlanejamentoMensalDeGastoDto mapFromPlanoDeGasto(PlanejamentoMensalDeGasto source);

    PlanejamentoMensalDeGasto mapFromPladoDeGastoDto(PlanejamentoMensalDeGastoDto source);

}
