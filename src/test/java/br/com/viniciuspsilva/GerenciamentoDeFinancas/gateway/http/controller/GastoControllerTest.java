package br.com.viniciuspsilva.GerenciamentoDeFinancas.gateway.http.controller;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.exception.dto.DefaultErrorDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.exception.gasto.GastoNotFoundException;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.GastoDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.GastoEntity;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.service.GastoService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

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
        GastoEntity gastoEntity = Fixture.from(GastoEntity.class).gimme("gasto");


        when(gastoService.cadastrarGasto(any(GastoEntity.class))).thenReturn(gastoEntity);

        MvcResult mvcResult = mockMvc.perform(post("/financas/gasto")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(gastoDto))

        ).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        Assertions.assertEquals(HttpStatus.CREATED.value(), response.getStatus());

        verify(gastoService, times(1)).cadastrarGasto(any(GastoEntity.class));
    }

    @Test
    public void deveAtualizarUmGasto() throws Exception {

        GastoDto gastoDto = Fixture.from(GastoDto.class).gimme("update-gasto");
        GastoEntity gastoEntity = Fixture.from(GastoEntity.class).gimme("gasto");
        gastoEntity.setPrioridade(gastoDto.getPrioridade());
        gastoEntity.setNome(gastoDto.getNome());
        gastoEntity.setDescricao(gastoDto.getDescricao());


        when(gastoService.atualizar(any(GastoEntity.class), any(Integer.class))).thenReturn(gastoEntity);

        MvcResult mvcResult = mockMvc.perform(patch("/financas/gasto/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(gastoDto))

        ).andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());

        verify(gastoService, times(1)).atualizar(any(GastoEntity.class), any(Integer.class));
    }

    @Test
    public void deveListarGastos() throws Exception {

        GastoEntity gastoEntity = Fixture.from(GastoEntity.class).gimme("gasto");

        when(gastoService.listarGastos()).thenReturn(List.of(gastoEntity));

        MvcResult mvcResult = mockMvc.perform(get("/financas/gasto")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        String responseBody = response.getContentAsString();
        GastoDto responseObject = objectMapper.readValue(responseBody, new TypeReference<List<GastoDto>>() {
        }).get(0);

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assertions.assertEquals(gastoEntity.getId(), responseObject.getId());
        Assertions.assertEquals(gastoEntity.getNome(), responseObject.getNome());
        Assertions.assertEquals(gastoEntity.getDescricao(), responseObject.getDescricao());
        Assertions.assertEquals(gastoEntity.getStatus(), responseObject.getStatus());
        Assertions.assertEquals(gastoEntity.getPlanoDeGasto().getId(), responseObject.getIdPlanoDeGasto());
        Assertions.assertEquals(gastoEntity.getCategoriaEntity().getId(), responseObject.getIdCategoria());
        Assertions.assertEquals(gastoEntity.getDataCriacao(), responseObject.getDataCriacao());
        Assertions.assertEquals(gastoEntity.getMesReferencia(), responseObject.getMesReferencia());
        Assertions.assertEquals(gastoEntity.getDataVencimento(), responseObject.getDataVencimento());
        Assertions.assertEquals(gastoEntity.getValor(), responseObject.getValor());
        Assertions.assertEquals(gastoEntity.getParcelaAtual(), responseObject.getParcelaAtual());
        Assertions.assertEquals(gastoEntity.getTotalParcelas(), responseObject.getTotalParcelas());
        Assertions.assertEquals(gastoEntity.getTipo(), responseObject.getTipo());

        verify(gastoService, times(1)).listarGastos();

    }

    @Test
    public void deveBuscarGastoPorId() throws Exception {

        GastoEntity gastoEntity = Fixture.from(GastoEntity.class).gimme("gasto");

        when(gastoService.buscar(any(Integer.class))).thenReturn(gastoEntity);

        MvcResult mvcResult = mockMvc.perform(get("/financas/gasto/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        String responseBody = response.getContentAsString();
        GastoDto responseObject = objectMapper.readValue(responseBody, new TypeReference<GastoDto>() {
        });

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assertions.assertEquals(gastoEntity.getId(), responseObject.getId());
        Assertions.assertEquals(gastoEntity.getNome(), responseObject.getNome());
        Assertions.assertEquals(gastoEntity.getDescricao(), responseObject.getDescricao());
        Assertions.assertEquals(gastoEntity.getStatus(), responseObject.getStatus());
        Assertions.assertEquals(gastoEntity.getPlanoDeGasto().getId(), responseObject.getIdPlanoDeGasto());
        Assertions.assertEquals(gastoEntity.getCategoriaEntity().getId(), responseObject.getIdCategoria());
        Assertions.assertEquals(gastoEntity.getDataCriacao(), responseObject.getDataCriacao());
        Assertions.assertEquals(gastoEntity.getMesReferencia(), responseObject.getMesReferencia());
        Assertions.assertEquals(gastoEntity.getDataVencimento(), responseObject.getDataVencimento());
        Assertions.assertEquals(gastoEntity.getValor(), responseObject.getValor());
        Assertions.assertEquals(gastoEntity.getParcelaAtual(), responseObject.getParcelaAtual());
        Assertions.assertEquals(gastoEntity.getTotalParcelas(), responseObject.getTotalParcelas());
        Assertions.assertEquals(gastoEntity.getTipo(), responseObject.getTipo());

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
