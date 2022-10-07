package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.mappers;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.GastoDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.GastoUpdatedDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.CategoriaEntity;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.GastoEntity;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.PlanejamentoMensalDeGastoEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.Objects;

@Mapper
public abstract class GastoMapper {
    public static GastoMapper INSTANCE = Mappers.getMapper(GastoMapper.class);

    public abstract GastoDto mapFromGasto(GastoEntity source);

    public abstract GastoUpdatedDto mapFromGastoToGastoUpdatedDto(GastoEntity source);

    public abstract GastoEntity mapFromGastoDto(GastoDto sorce);

    public abstract GastoEntity mapFromGastoUpdatedDto(GastoUpdatedDto source);

    public abstract Iterable<GastoDto> mapFromGastoList(Iterable<GastoEntity> source);

    @AfterMapping
    public void afterMappingPlanoDeGasto(final GastoDto source, @MappingTarget final GastoEntity target) {
        final PlanejamentoMensalDeGastoEntity planoDeGasto = new PlanejamentoMensalDeGastoEntity();
        planoDeGasto.setId(source.getIdPlanoDeGasto());
        target.setPlanoDeGasto(planoDeGasto);
    }

    @AfterMapping
    public void afterMappingCategoria(GastoDto source, @MappingTarget GastoEntity target) {
        final CategoriaEntity categoriaEntity = new CategoriaEntity();
        categoriaEntity.setId(source.getIdCategoria());
        target.setCategoriaEntity(categoriaEntity);
    }

    @AfterMapping
    public void afterMappingGastoDto(GastoEntity source, @MappingTarget GastoDto target){

        if (Objects.nonNull(source) && Objects.nonNull(source.getPlanoDeGasto()))
            target.setIdPlanoDeGasto(source.getPlanoDeGasto().getId());

        if (Objects.nonNull(source) && Objects.nonNull(source.getCategoriaEntity()))
            target.setIdCategoria(source.getCategoriaEntity().getId());
    }

}
