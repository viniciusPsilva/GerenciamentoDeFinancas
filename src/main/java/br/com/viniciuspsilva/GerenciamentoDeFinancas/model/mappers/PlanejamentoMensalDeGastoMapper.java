package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.mappers;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.PlanejamentoMensalDeGastoDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.domain.PlanejamentoMensalDeGasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.PlanejamentoMensalDeGastoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlanejamentoMensalDeGastoMapper {

    PlanejamentoMensalDeGastoMapper INSTANCE = Mappers.getMapper(PlanejamentoMensalDeGastoMapper.class);

    PlanejamentoMensalDeGasto mapFromPlanoDeGastoEntity(PlanejamentoMensalDeGastoEntity source);

    PlanejamentoMensalDeGasto mapFromPladoDeGastoDto(PlanejamentoMensalDeGastoDto source);

    PlanejamentoMensalDeGastoDto mapToDto(PlanejamentoMensalDeGasto source);

    PlanejamentoMensalDeGastoEntity mapToEntity(PlanejamentoMensalDeGasto source);


}
