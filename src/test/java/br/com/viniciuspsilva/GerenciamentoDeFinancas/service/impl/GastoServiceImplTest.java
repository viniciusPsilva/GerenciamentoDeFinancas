package br.com.viniciuspsilva.GerenciamentoDeFinancas.service.impl;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.fixture.GastoFixture;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.GastoDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.Gasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enumerations.Prioridade;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enumerations.StatusGasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enumerations.Tipo;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.gateway.repository.GastoRepository;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.mappers.GastoMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
public class GastoServiceImplTest {

    @Mock
    private GastoRepository repository;

    @Mock
    private GastoMapper gastoMapper;

    @InjectMocks
    private GastoServiceImpl gastoService;



    //TODO ajustar testes
    //@Test
    public void cadastrarGasto(){
        Mockito.when(gastoMapper.mapFromGastoDto(any(GastoDto.class))).thenReturn(GastoFixture.gasto());
        Mockito.when(gastoMapper.mapFromGasto(any(Gasto.class))).thenReturn(GastoFixture.gastoDto());
        Mockito.when(repository.save(any(Gasto.class))).thenReturn(GastoFixture.gasto());

        GastoDto gastoPersistido = gastoService.cadastrarGasto(GastoFixture.gastoDto());

        assertNotNull(gastoPersistido);
        assertEquals("Gasto Teste", gastoPersistido.getNome());
        assertEquals("Gasto Teste", gastoPersistido.getDescricao());
        assertEquals(BigDecimal.valueOf(200.00), gastoPersistido.getValor());
        assertEquals(Tipo.GASTO, gastoPersistido.getTipo());
        assertEquals(StatusGasto.EM_ABERTO, gastoPersistido.getStatus());
        assertEquals(Prioridade.BAIXA, gastoPersistido.getPrioridade());
    }
}
