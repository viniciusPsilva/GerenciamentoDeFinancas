package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.mappers;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.PlanejamentoMensalDeGastoDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.domain.Gasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.domain.PlanejamentoMensalDeGasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.GastoEntity;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.PlanejamentoMensalDeGastoEntity;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enums.MesReferencia;
import org.junit.Before;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class PlanejamentoMensalDeGastoMapper {

    public static PlanejamentoMensalDeGastoMapper INSTANCE = Mappers.getMapper(PlanejamentoMensalDeGastoMapper.class);

    public abstract PlanejamentoMensalDeGasto mapFromPlanoDeGastoEntity(PlanejamentoMensalDeGastoEntity source);

    @Mapping(target = "mesReferencia", source = "mesReferencia", ignore = true)
    public abstract PlanejamentoMensalDeGasto mapFromPladoDeGastoDto(PlanejamentoMensalDeGastoDto source);

    public abstract PlanejamentoMensalDeGastoDto mapToDto(PlanejamentoMensalDeGasto source);

    public abstract PlanejamentoMensalDeGastoEntity mapToEntity(PlanejamentoMensalDeGasto source);

    @BeforeMapping
    public void beforePlanejamentoMensalDeGasto(final PlanejamentoMensalDeGastoDto source, @MappingTarget final PlanejamentoMensalDeGasto target) {
        if (MesReferencia.exists(source.getMesReferencia())){
            target.setMesReferencia(MesReferencia.of(source.getMesReferencia()));
        }
    }

}
