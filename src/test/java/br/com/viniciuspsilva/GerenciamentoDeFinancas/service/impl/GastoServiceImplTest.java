package br.com.viniciuspsilva.GerenciamentoDeFinancas.service.impl;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.exception.gasto.GastoNotFoundException;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.fixture.GastoFixture;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.GastoDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.Gasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enumerations.Prioridade;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enumerations.StatusGasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enumerations.Tipo;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.gateway.repository.GastoRepository;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.mappers.GastoMapper;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
public class GastoServiceImplTest {

    @Mock
    private GastoRepository repository;

    @InjectMocks
    private GastoServiceImpl gastoService;

    @BeforeEach
    public void setUp() {
        FixtureFactoryLoader.loadTemplates("br.com.viniciuspsilva.GerenciamentoDeFinancas.template");
    }

    @Test
    public void cadastrarGasto() {
        GastoDto gastoDto = Fixture.from(GastoDto.class).gimme("gasto");
        Gasto gasto = Fixture.from(Gasto.class).gimme("gasto");

        Mockito.when(repository.save(any(Gasto.class))).thenReturn(gasto);

        GastoDto gastoPersistido = gastoService.cadastrarGasto(gastoDto);

        assertNotNull(gastoPersistido);
        assertEquals(gasto.getNome(), gastoPersistido.getNome());
        assertEquals(gasto.getDescricao(), gastoPersistido.getDescricao());
        assertEquals(gasto.getValor(), gastoPersistido.getValor());
        assertEquals(gasto.getTipo(), gastoPersistido.getTipo());
        assertEquals(gasto.getStatus(), gastoPersistido.getStatus());
        assertEquals(gasto.getPrioridade(), gastoPersistido.getPrioridade());
    }


    @Test
    public void DeveLitarGatos() {
        Gasto gasto = Fixture.from(Gasto.class).gimme("gasto");
        Mockito.when(repository.findAll()).thenReturn(Collections.singletonList(gasto));

        Iterable<GastoDto> gastoDtos = gastoService.listarGastos();

        gastoDtos.forEach(gastoDto -> {
            assertEquals(gasto.getId(), gastoDto.getId());
            assertEquals(gasto.getNome(), gastoDto.getNome());
            assertEquals(gasto.getDescricao(), gastoDto.getDescricao());
            assertEquals(gasto.getValor(), gastoDto.getValor());
            assertEquals(gasto.getTipo(), gastoDto.getTipo());
            assertEquals(gasto.getStatus(), gastoDto.getStatus());
            assertEquals(gasto.getPrioridade(), gastoDto.getPrioridade());
            assertEquals(gasto.getDataReferencia(), gastoDto.getDataReferencia());
            assertEquals(gasto.getDataVencimento(), gastoDto.getDataVencimento());
            assertEquals(gasto.getTotalParcelas(), gastoDto.getTotalParcelas());
            assertEquals(gasto.getParcelaAtual(), gastoDto.getParcelaAtual());
            assertEquals(gasto.getDataCriacao(), gastoDto.getDataCriacao());

        });
    }

    @Test
    public void deveBuscarUmGastoApartirDeUmId() {

        Gasto gasto = Fixture.from(Gasto.class).gimme("gasto");
        Mockito.when(repository.findById(any(Integer.class))).thenReturn(Optional.of(gasto));

        GastoDto gastoDto = gastoService.buscar(1);

        assertEquals(gasto.getId(), gastoDto.getId());
        assertEquals(gasto.getNome(), gastoDto.getNome());
        assertEquals(gasto.getDescricao(), gastoDto.getDescricao());
        assertEquals(gasto.getValor(), gastoDto.getValor());
        assertEquals(gasto.getTipo(), gastoDto.getTipo());
        assertEquals(gasto.getStatus(), gastoDto.getStatus());
        assertEquals(gasto.getPrioridade(), gastoDto.getPrioridade());
        assertEquals(gasto.getDataReferencia(), gastoDto.getDataReferencia());
        assertEquals(gasto.getDataVencimento(), gastoDto.getDataVencimento());
        assertEquals(gasto.getTotalParcelas(), gastoDto.getTotalParcelas());
        assertEquals(gasto.getParcelaAtual(), gastoDto.getParcelaAtual());
        assertEquals(gasto.getDataCriacao(), gastoDto.getDataCriacao());
    }

    @Test
    public void deveRetornarGastoNotFoundExceptionQuandoGastoNaoExistir(){

        final String expectedErrorMessage = "O gasto com id informado não foi encontrado";

        Mockito.when(repository.findById(any(Integer.class))).thenReturn(Optional.empty());

        GastoNotFoundException ex = assertThrows(GastoNotFoundException.class, () -> {
            gastoService.buscar(1);
        });

        assertEquals(expectedErrorMessage, ex.getMessage());

    }

}
