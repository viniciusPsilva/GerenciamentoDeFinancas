package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.mappers;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.GastoDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.GastoUpdatedDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.Categoria;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.Gasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.PlanejamentoMensalDeGasto;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.Objects;

@Mapper
public abstract class GastoUpdatedMapper {
    public static GastoUpdatedMapper INSTANCE = Mappers.getMapper(GastoUpdatedMapper.class);

    public abstract GastoUpdatedDto mapFromGasto(Gasto source);

    public abstract Gasto mapFromGastoUpdatedDto(GastoUpdatedDto source);

    @AfterMapping
    public void afterMappingPlanoDeGasto(final GastoUpdatedDto source, @MappingTarget final Gasto target) {
        final PlanejamentoMensalDeGasto planoDeGasto = new PlanejamentoMensalDeGasto();
        planoDeGasto.setId(source.getIdPlanoDeGasto());
        target.setPlanoDeGasto(planoDeGasto);
    }

    @AfterMapping
    public void afterMappingCategoria(GastoUpdatedDto source, @MappingTarget Gasto target) {
        final Categoria categoria = new Categoria();
        categoria.setId(source.getIdCategoria());
        target.setCategoria(categoria);
    }

    @AfterMapping
    public void afterMappingGastoDto(Gasto source, @MappingTarget GastoUpdatedDto target){

        if (Objects.nonNull(source) && Objects.nonNull(source.getPlanoDeGasto()))
            target.setIdPlanoDeGasto(source.getPlanoDeGasto().getId());

        if (Objects.nonNull(source) && Objects.nonNull(source.getCategoria()))
            target.setIdCategoria(source.getCategoria().getId());
    }

}
