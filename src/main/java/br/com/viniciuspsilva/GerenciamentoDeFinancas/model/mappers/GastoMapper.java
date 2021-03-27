package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.mappers;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.GastoDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.Categoria;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.Gasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.PlanoDeGasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.service.CategoriaService;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.service.PlanoDeGastoService;
import lombok.RequiredArgsConstructor;
import org.junit.After;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import javax.crypto.spec.PSource;
import java.time.LocalDate;
import java.util.List;

@Mapper
public abstract class GastoMapper {
    public static GastoMapper INSTANCE = Mappers.getMapper(GastoMapper.class);

    public abstract GastoDto mapFromGasto(Gasto source);

    public abstract Gasto mapFromGastoDto(GastoDto sorce);

    public abstract Iterable<GastoDto> mapFromGastoList(Iterable<Gasto> source);

    @AfterMapping
    public void afterMappingPlanoDeGasto(final GastoDto source, @MappingTarget final Gasto target) {
        final PlanoDeGasto planoDeGasto = new PlanoDeGasto();
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
