package br.com.viniciuspsilva.GerenciamentoDeFinancas.gateway.http.controller;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.exception.dto.DefaultErrorDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.exception.gasto.GastoNotFoundException;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.fixture.GastoFixture;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.GastoDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.Gasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.service.GastoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.TestExecutionResult;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.net.URI;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GastoController.class)
public class GastoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private GastoService gastoService;

    @BeforeEach
    public void setUp() {
        FixtureFactoryLoader.loadTemplates("br.com.viniciuspsilva.GerenciamentoDeFinancas.template");
    }


    @Test
    public void deveCadastrarUmGasto() throws Exception {

        GastoDto gastoDto = Fixture.from(GastoDto.class).gimme("gasto");
        Gasto gasto = Fixture.from(Gasto.class).gimme("gasto");


        when(gastoService.cadastrarGasto(any(Gasto.class))).thenReturn(gasto);

        MvcResult mvcResult = mockMvc.perform(post("/financas/gasto")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(gastoDto))

        ).andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        Assertions.assertEquals(HttpStatus.CREATED.value(), response.getStatus());

        verify(gastoService, times(1)).cadastrarGasto(any(Gasto.class));
    }

    @Test
    public void deveAtualizarUmGasto() throws Exception {

        GastoDto gastoDto = Fixture.from(GastoDto.class).gimme("update-gasto");
        Gasto gasto = Fixture.from(Gasto.class).gimme("gasto");
        gasto.setPrioridade(gastoDto.getPrioridade());
        gasto.setNome(gastoDto.getNome());
        gasto.setDescricao(gastoDto.getDescricao());


        when(gastoService.atualizar(any(Gasto.class), any(Integer.class))).thenReturn(gasto);

        MvcResult mvcResult = mockMvc.perform(patch("/financas/gasto/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(gastoDto))

        ).andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());

        verify(gastoService, times(1)).atualizar(any(Gasto.class), any(Integer.class));
    }

    @Test
    public void deveListarGastos() throws Exception {

        Gasto gasto = Fixture.from(Gasto.class).gimme("gasto");

        when(gastoService.listarGastos()).thenReturn(List.of(gasto));

        MvcResult mvcResult = mockMvc.perform(get("/financas/gasto")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        String responseBody = response.getContentAsString();
        GastoDto responseObject = objectMapper.readValue(responseBody, new TypeReference<List<GastoDto>>() {
        }).get(0);

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assertions.assertEquals(gasto.getId(), responseObject.getId());
        Assertions.assertEquals(gasto.getNome(), responseObject.getNome());
        Assertions.assertEquals(gasto.getDescricao(), responseObject.getDescricao());
        Assertions.assertEquals(gasto.getStatus(), responseObject.getStatus());
        Assertions.assertEquals(gasto.getPlanoDeGasto().getId(), responseObject.getIdPlanoDeGasto());
        Assertions.assertEquals(gasto.getCategoria().getId(), responseObject.getIdCategoria());
        Assertions.assertEquals(gasto.getDataCriacao(), responseObject.getDataCriacao());
        Assertions.assertEquals(gasto.getMesReferencia(), responseObject.getMesReferencia());
        Assertions.assertEquals(gasto.getDataVencimento(), responseObject.getDataVencimento());
        Assertions.assertEquals(gasto.getValor(), responseObject.getValor());
        Assertions.assertEquals(gasto.getParcelaAtual(), responseObject.getParcelaAtual());
        Assertions.assertEquals(gasto.getTotalParcelas(), responseObject.getTotalParcelas());
        Assertions.assertEquals(gasto.getTipo(), responseObject.getTipo());

        verify(gastoService, times(1)).listarGastos();

    }

    @Test
    public void deveBuscarGastoPorId() throws Exception {

        Gasto gasto = Fixture.from(Gasto.class).gimme("gasto");

        when(gastoService.buscar(any(Integer.class))).thenReturn(gasto);

        MvcResult mvcResult = mockMvc.perform(get("/financas/gasto/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        String responseBody = response.getContentAsString();
        GastoDto responseObject = objectMapper.readValue(responseBody, new TypeReference<GastoDto>() {
        });

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assertions.assertEquals(gasto.getId(), responseObject.getId());
        Assertions.assertEquals(gasto.getNome(), responseObject.getNome());
        Assertions.assertEquals(gasto.getDescricao(), responseObject.getDescricao());
        Assertions.assertEquals(gasto.getStatus(), responseObject.getStatus());
        Assertions.assertEquals(gasto.getPlanoDeGasto().getId(), responseObject.getIdPlanoDeGasto());
        Assertions.assertEquals(gasto.getCategoria().getId(), responseObject.getIdCategoria());
        Assertions.assertEquals(gasto.getDataCriacao(), responseObject.getDataCriacao());
        Assertions.assertEquals(gasto.getMesReferencia(), responseObject.getMesReferencia());
        Assertions.assertEquals(gasto.getDataVencimento(), responseObject.getDataVencimento());
        Assertions.assertEquals(gasto.getValor(), responseObject.getValor());
        Assertions.assertEquals(gasto.getParcelaAtual(), responseObject.getParcelaAtual());
        Assertions.assertEquals(gasto.getTotalParcelas(), responseObject.getTotalParcelas());
        Assertions.assertEquals(gasto.getTipo(), responseObject.getTipo());

        verify(gastoService, times(1)).buscar(any(Integer.class));
    }

    @Test
    public void deveRetornarBadRequestQuandoGastoNaoExistir() throws Exception {

        final String expectedErrorMessage = "O gasto com id informado nao foi encontrado";
        when(gastoService.buscar(any(Integer.class))).thenThrow(new GastoNotFoundException(expectedErrorMessage));

        MvcResult mvcResult = mockMvc.perform(get("/financas/gasto/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        String responseBody = response.getContentAsString();

        DefaultErrorDto responseObject = objectMapper.readValue(responseBody, new TypeReference<DefaultErrorDto>() {
        });

        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        Assertions.assertEquals(expectedErrorMessage, responseObject.getMensagem());

        verify(gastoService, times(1)).buscar(any(Integer.class));

    }

    @Test
    public void deveDeletarUmGastoPeloId() throws Exception {
        doNothing().when(gastoService).deletar(any(Integer.class));

        MvcResult mvcResult = mockMvc.perform(delete("/financas/gasto/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        Assertions.assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());

        Mockito.verify(gastoService, times(1)).deletar(any(Integer.class));
    }

}
