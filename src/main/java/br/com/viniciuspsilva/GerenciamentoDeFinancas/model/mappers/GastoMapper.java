package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.mappers;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.GastoDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.domain.Gasto;
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

    public abstract Gasto mapFromEntity(GastoEntity source);

    public abstract Gasto mapFromDto(GastoDto sorce);

    public abstract GastoDto mapToDto(Gasto gasto);

    public abstract GastoEntity mapToEntity(Gasto gasto);

    public abstract Iterable<Gasto> mapFromEntityList(Iterable<GastoEntity> source);

    public abstract Iterable<GastoDto> mapToDtoList(Iterable<Gasto> source);

    @AfterMapping
    public void afterMappingPlanoDeGasto(final Gasto source, @MappingTarget final GastoEntity target) {
        final PlanejamentoMensalDeGastoEntity planoDeGasto = new PlanejamentoMensalDeGastoEntity();
        planoDeGasto.setId(source.getIdPlanoDeGasto());
        target.setPlanoDeGasto(planoDeGasto);
    }

    @AfterMapping
    public void afterMappingCategoria(Gasto source, @MappingTarget GastoEntity target) {
        final CategoriaEntity categoriaEntity = new CategoriaEntity();
        categoriaEntity.setId(source.getIdCategoria());
        target.setCategoria(categoriaEntity);
    }

    @AfterMapping
    public void afterMappingGastoFromGastoEntity(GastoEntity source, @MappingTarget Gasto target){

        if (Objects.nonNull(source) && Objects.nonNull(source.getPlanoDeGasto()))
            target.setIdPlanoDeGasto(source.getPlanoDeGasto().getId());

        if (Objects.nonNull(source) && Objects.nonNull(source.getCategoria()))
            target.setIdCategoria(source.getCategoria().getId());
    }

}
