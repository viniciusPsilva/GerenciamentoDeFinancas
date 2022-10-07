package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.mappers;

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
public abstract class GastoUpdatedMapper {
    public static GastoUpdatedMapper INSTANCE = Mappers.getMapper(GastoUpdatedMapper.class);

    public abstract GastoUpdatedDto mapFromGasto(GastoEntity source);

    public abstract GastoEntity mapFromGastoUpdatedDto(GastoUpdatedDto source);

    @AfterMapping
    public void afterMappingPlanoDeGasto(final GastoUpdatedDto source, @MappingTarget final GastoEntity target) {
        final PlanejamentoMensalDeGastoEntity planoDeGasto = new PlanejamentoMensalDeGastoEntity();
        planoDeGasto.setId(source.getIdPlanoDeGasto());
        target.setPlanoDeGasto(planoDeGasto);
    }

    @AfterMapping
    public void afterMappingCategoria(GastoUpdatedDto source, @MappingTarget GastoEntity target) {
        final CategoriaEntity categoriaEntity = new CategoriaEntity();
        categoriaEntity.setId(source.getIdCategoria());
        target.setCategoriaEntity(categoriaEntity);
    }

    @AfterMapping
    public void afterMappingGastoDto(GastoEntity source, @MappingTarget GastoUpdatedDto target){

        if (Objects.nonNull(source) && Objects.nonNull(source.getPlanoDeGasto()))
            target.setIdPlanoDeGasto(source.getPlanoDeGasto().getId());

        if (Objects.nonNull(source) && Objects.nonNull(source.getCategoriaEntity()))
            target.setIdCategoria(source.getCategoriaEntity().getId());
    }

}
