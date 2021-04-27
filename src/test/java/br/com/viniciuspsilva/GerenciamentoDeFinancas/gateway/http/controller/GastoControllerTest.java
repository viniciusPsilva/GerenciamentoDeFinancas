package br.com.viniciuspsilva.GerenciamentoDeFinancas.gateway.http.controller;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.exception.dto.DefaultErrorDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.exception.gasto.GastoNotFoundException;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.fixture.GastoFixture;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.GastoDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.service.GastoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.TestExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
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

        when(gastoService.cadastrarGasto(any(GastoDto.class))).thenReturn(gastoDto);

        MvcResult mvcResult = mockMvc.perform(post("/financas/gasto")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(gastoDto))

        ).andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        Assertions.assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    public void deveListarGastos() throws Exception {

        GastoDto gastoDto = Fixture.from(GastoDto.class).gimme("gasto");

        when(gastoService.listarGastos()).thenReturn(List.of(gastoDto));

        MvcResult mvcResult = mockMvc.perform(get("/financas/gasto")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        String responseBody = response.getContentAsString();
        GastoDto responseObject = objectMapper.readValue(responseBody, new TypeReference<List<GastoDto>>() {
        }).get(0);

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assertions.assertEquals(gastoDto.getId(), responseObject.getId());
        Assertions.assertEquals(gastoDto.getNome(), responseObject.getNome());
        Assertions.assertEquals(gastoDto.getDescricao(), responseObject.getDescricao());
        Assertions.assertEquals(gastoDto.getStatus(), responseObject.getStatus());
        Assertions.assertEquals(gastoDto.getIdPlanoDeGasto(), responseObject.getIdPlanoDeGasto());
        Assertions.assertEquals(gastoDto.getIdCategoria(), responseObject.getIdCategoria());
        Assertions.assertEquals(gastoDto.getDataCriacao(), responseObject.getDataCriacao());
        Assertions.assertEquals(gastoDto.getMesReferencia(), responseObject.getMesReferencia());
        Assertions.assertEquals(gastoDto.getDataVencimento(), responseObject.getDataVencimento());
        Assertions.assertEquals(gastoDto.getValor(), responseObject.getValor());
        Assertions.assertEquals(gastoDto.getParcelaAtual(), responseObject.getParcelaAtual());
        Assertions.assertEquals(gastoDto.getTotalParcelas(), responseObject.getTotalParcelas());
        Assertions.assertEquals(gastoDto.getTipo(), responseObject.getTipo());


    }

    @Test
    public void deveBuscarGastoPorId() throws Exception {

        GastoDto gastoDto = Fixture.from(GastoDto.class).gimme("gasto");

        when(gastoService.buscar(any(Integer.class))).thenReturn(gastoDto);

        MvcResult mvcResult = mockMvc.perform(get("/financas/gasto/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        String responseBody = response.getContentAsString();
        GastoDto responseObject = objectMapper.readValue(responseBody, new TypeReference<GastoDto>() {
        });

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assertions.assertEquals(gastoDto.getId(), responseObject.getId());
        Assertions.assertEquals(gastoDto.getNome(), responseObject.getNome());
        Assertions.assertEquals(gastoDto.getDescricao(), responseObject.getDescricao());
        Assertions.assertEquals(gastoDto.getStatus(), responseObject.getStatus());
        Assertions.assertEquals(gastoDto.getIdPlanoDeGasto(), responseObject.getIdPlanoDeGasto());
        Assertions.assertEquals(gastoDto.getIdCategoria(), responseObject.getIdCategoria());
        Assertions.assertEquals(gastoDto.getDataCriacao(), responseObject.getDataCriacao());
        Assertions.assertEquals(gastoDto.getMesReferencia(), responseObject.getMesReferencia());
        Assertions.assertEquals(gastoDto.getDataVencimento(), responseObject.getDataVencimento());
        Assertions.assertEquals(gastoDto.getValor(), responseObject.getValor());
        Assertions.assertEquals(gastoDto.getParcelaAtual(), responseObject.getParcelaAtual());
        Assertions.assertEquals(gastoDto.getTotalParcelas(), responseObject.getTotalParcelas());
        Assertions.assertEquals(gastoDto.getTipo(), responseObject.getTipo());
    }

    @Test
    public void deveRetornarBadRequestQuandoGastoNaoExistir() throws Exception {

        GastoDto gastoDto = Fixture.from(GastoDto.class).gimme("gasto");

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

    }


}
