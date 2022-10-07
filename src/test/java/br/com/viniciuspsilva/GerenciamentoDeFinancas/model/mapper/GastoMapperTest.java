package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.mapper;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.GastoDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.domain.Gasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.GastoEntity;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.mappers.GastoMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GastoMapperTest {

    @BeforeEach
    public void beforeEach(){
        FixtureFactoryLoader.loadTemplates("br.com.viniciuspsilva.GerenciamentoDeFinancas.template");
    }

    @Test
    public void mapToDto(){
        Gasto source = Fixture.from(Gasto.class).gimme("valid");
        GastoDto target = GastoMapper.INSTANCE.mapToDto(source);

        Assertions.assertEquals(source.getId(), target.getId());
        Assertions.assertEquals(source.getNome(), target.getNome());
        Assertions.assertEquals(source.getDescricao(), target.getDescricao());
        Assertions.assertEquals(source.getDescricao(), target.getDescricao());
        Assertions.assertEquals(source.getValor(), target.getValor());
        Assertions.assertEquals(source.getMesReferencia(), target.getMesReferencia());
        Assertions.assertEquals(source.getDataVencimento(), target.getDataVencimento());
        Assertions.assertEquals(source.getTipo(), target.getTipo());
        Assertions.assertEquals(source.getStatus(), target.getStatus());
        Assertions.assertEquals(source.getPrioridade(), target.getPrioridade());
        Assertions.assertEquals(source.getTotalParcelas(), target.getTotalParcelas());
        Assertions.assertEquals(source.getParcelaAtual(), target.getParcelaAtual());
        Assertions.assertEquals(source.getDataCriacao(), target.getDataCriacao());
        Assertions.assertEquals(source.getIdPlanoDeGasto(), target.getIdPlanoDeGasto());
        Assertions.assertEquals(source.getIdCategoria(), target.getIdCategoria());
    }

    @Test
    public void mapFromDto(){
        GastoDto source = Fixture.from(GastoDto.class).gimme("gasto");
        Gasto target = GastoMapper.INSTANCE.mapFromDto(source);

        Assertions.assertEquals(source.getId(), target.getId());
        Assertions.assertEquals(source.getNome(), target.getNome());
        Assertions.assertEquals(source.getDescricao(), target.getDescricao());
        Assertions.assertEquals(source.getDescricao(), target.getDescricao());
        Assertions.assertEquals(source.getValor(), target.getValor());
        Assertions.assertEquals(source.getMesReferencia(), target.getMesReferencia());
        Assertions.assertEquals(source.getDataVencimento(), target.getDataVencimento());
        Assertions.assertEquals(source.getTipo(), target.getTipo());
        Assertions.assertEquals(source.getStatus(), target.getStatus());
        Assertions.assertEquals(source.getPrioridade(), target.getPrioridade());
        Assertions.assertEquals(source.getTotalParcelas(), target.getTotalParcelas());
        Assertions.assertEquals(source.getParcelaAtual(), target.getParcelaAtual());
        Assertions.assertEquals(source.getDataCriacao(), target.getDataCriacao());
        Assertions.assertEquals(source.getIdPlanoDeGasto(), target.getIdPlanoDeGasto());
        Assertions.assertEquals(source.getIdCategoria(), target.getIdCategoria());
    }

    @Test
    public void mapToDtoList(){
        Gasto source = Fixture.from(Gasto.class).gimme("valid");

        List<Gasto> gastos = new ArrayList<>();
        gastos.add(source);
        gastos.add(source);

        Iterable<GastoDto> targetList = GastoMapper.INSTANCE.mapToDtoList(gastos);

        GastoDto target = targetList.iterator().next();

        int tamanhoListaEsperada = 2;
        Assertions.assertEquals(tamanhoListaEsperada,((Collection<?>) targetList).size());
        Assertions.assertEquals(source.getId(), target.getId());
        Assertions.assertEquals(source.getNome(), target.getNome());
        Assertions.assertEquals(source.getDescricao(), target.getDescricao());
        Assertions.assertEquals(source.getDescricao(), target.getDescricao());
        Assertions.assertEquals(source.getValor(), target.getValor());
        Assertions.assertEquals(source.getMesReferencia(), target.getMesReferencia());
        Assertions.assertEquals(source.getDataVencimento(), target.getDataVencimento());
        Assertions.assertEquals(source.getTipo(), target.getTipo());
        Assertions.assertEquals(source.getStatus(), target.getStatus());
        Assertions.assertEquals(source.getPrioridade(), target.getPrioridade());
        Assertions.assertEquals(source.getTotalParcelas(), target.getTotalParcelas());
        Assertions.assertEquals(source.getParcelaAtual(), target.getParcelaAtual());
        Assertions.assertEquals(source.getDataCriacao(), target.getDataCriacao());
        Assertions.assertEquals(source.getIdPlanoDeGasto(), target.getIdPlanoDeGasto());
        Assertions.assertEquals(source.getIdCategoria(), target.getIdCategoria());
    }

    @Test
    public void mapFromEntity(){
        GastoEntity source = Fixture.from(GastoEntity.class).gimme("gasto");
        Gasto target = GastoMapper.INSTANCE.mapFromEntity(source);

        Assertions.assertEquals(source.getId(), target.getId());
        Assertions.assertEquals(source.getNome(), target.getNome());
        Assertions.assertEquals(source.getDescricao(), target.getDescricao());
        Assertions.assertEquals(source.getValor(), target.getValor());
        Assertions.assertEquals(source.getMesReferencia(), target.getMesReferencia());
        Assertions.assertEquals(source.getDataVencimento(), target.getDataVencimento());
        Assertions.assertEquals(source.getTipo(), target.getTipo());
        Assertions.assertEquals(source.getStatus(), target.getStatus());
        Assertions.assertEquals(source.getPrioridade(), target.getPrioridade());
        Assertions.assertEquals(source.getTotalParcelas(), target.getTotalParcelas());
        Assertions.assertEquals(source.getParcelaAtual(), target.getParcelaAtual());
        Assertions.assertEquals(source.getDataCriacao(), target.getDataCriacao());
        Assertions.assertEquals(source.getPlanoDeGasto().getId(), target.getIdPlanoDeGasto());
        Assertions.assertEquals(source.getCategoria().getId(), target.getIdCategoria());
    }

    @Test
    public void mapToEntity(){
        Gasto source = Fixture.from(Gasto.class).gimme("valid");
        GastoEntity target = GastoMapper.INSTANCE.mapToEntity(source);

        Assertions.assertEquals(source.getId(), target.getId());
        Assertions.assertEquals(source.getNome(), target.getNome());
        Assertions.assertEquals(source.getDescricao(), target.getDescricao());
        Assertions.assertEquals(source.getValor(), target.getValor());
        Assertions.assertEquals(source.getMesReferencia(), target.getMesReferencia());
        Assertions.assertEquals(source.getDataVencimento(), target.getDataVencimento());
        Assertions.assertEquals(source.getTipo(), target.getTipo());
        Assertions.assertEquals(source.getStatus(), target.getStatus());
        Assertions.assertEquals(source.getPrioridade(), target.getPrioridade());
        Assertions.assertEquals(source.getTotalParcelas(), target.getTotalParcelas());
        Assertions.assertEquals(source.getParcelaAtual(), target.getParcelaAtual());
        Assertions.assertEquals(source.getDataCriacao(), target.getDataCriacao());
        Assertions.assertEquals(source.getIdPlanoDeGasto(),target.getPlanoDeGasto().getId());
        Assertions.assertEquals(source.getIdCategoria(), target.getCategoria().getId());
    }

    @Test
    public void mapFromEntityList(){
        GastoEntity source = Fixture.from(GastoEntity.class).gimme("gasto");

        List<GastoEntity> listaDeGastoEntity = new ArrayList<>();
        listaDeGastoEntity.add(source);
        listaDeGastoEntity.add(source);

        Iterable<Gasto> targetList = GastoMapper.INSTANCE.mapFromEntityList(listaDeGastoEntity);

        Gasto target = targetList.iterator().next();

        int tamanhoListaEsperada  = 2;

        Assertions.assertEquals(tamanhoListaEsperada , ((Collection<?>) targetList).size());
        Assertions.assertEquals(source.getId(), target.getId());
        Assertions.assertEquals(source.getNome(), target.getNome());
        Assertions.assertEquals(source.getDescricao(), target.getDescricao());
        Assertions.assertEquals(source.getValor(), target.getValor());
        Assertions.assertEquals(source.getMesReferencia(), target.getMesReferencia());
        Assertions.assertEquals(source.getDataVencimento(), target.getDataVencimento());
        Assertions.assertEquals(source.getTipo(), target.getTipo());
        Assertions.assertEquals(source.getStatus(), target.getStatus());
        Assertions.assertEquals(source.getPrioridade(), target.getPrioridade());
        Assertions.assertEquals(source.getTotalParcelas(), target.getTotalParcelas());
        Assertions.assertEquals(source.getParcelaAtual(), target.getParcelaAtual());
        Assertions.assertEquals(source.getDataCriacao(), target.getDataCriacao());
        Assertions.assertEquals(source.getPlanoDeGasto().getId(),target.getIdPlanoDeGasto());
        Assertions.assertEquals(source.getCategoria().getId(), target.getIdCategoria());
    }

}
