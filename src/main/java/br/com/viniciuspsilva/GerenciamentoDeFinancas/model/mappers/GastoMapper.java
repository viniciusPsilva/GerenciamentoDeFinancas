package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.mappers;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.GastoDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.Categoria;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.Gasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.PlanejamentoMensalDeGasto;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class GastoMapper {
    public static GastoMapper INSTANCE = Mappers.getMapper(GastoMapper.class);

    public abstract GastoDto mapFromGasto(Gasto source);

    public abstract Gasto mapFromGastoDto(GastoDto sorce);

    public abstract Iterable<GastoDto> mapFromGastoList(Iterable<Gasto> source);

    @AfterMapping
    public void afterMappingPlanoDeGasto(final GastoDto source, @MappingTarget final Gasto target) {
        final PlanejamentoMensalDeGasto planoDeGasto = new PlanejamentoMensalDeGasto();
        planoDeGasto.setId(source.getIdPlanoDeGasto());
        target.setPlanoDeGasto(planoDeGasto);
    }

    @AfterMapping
    public void afterMappingCategoria(GastoDto source, @MappingTarget Gasto target) {
        final Categoria categoria = new Categoria();
        categoria.setId(source.getIdCategoria());
        target.setCategoria(categoria);
    }


}
