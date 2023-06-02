package br.com.viniciuspsilva.GerenciamentoDeFinancas.gateway.http.controller;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.gateway.repository.CategoriaRepository;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.CategoriaDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.CategoriaEntity;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.service.impl.CategoriaServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(CategoriaController.class)
public class CategoriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CategoriaServiceImpl categoriaService;

    @MockBean
    private CategoriaRepository repository;

    @BeforeEach
    public void setUp(){
        FixtureFactoryLoader.loadTemplates("br.com.viniciuspsilva.GerenciamentoDeFinancas.template");
    }

    @Test
    public void deveCadastrarUmaCategoria() throws Exception {
        CategoriaEntity categoriaEntity = Fixture.from(CategoriaEntity.class).gimme("valid");
        Mockito.when(categoriaService.cadastrar(ArgumentMatchers.any(CategoriaEntity.class))).thenReturn(categoriaEntity);

        CategoriaDto categoriaDto = Fixture.from(CategoriaDto.class).gimme("valid");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/financas/categoria")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoriaDto)))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        Assertions.assertEquals(response.getStatus(), HttpStatus.CREATED.value());

        Mockito.verify(categoriaService, Mockito.times(1)).cadastrar(ArgumentMatchers.any(CategoriaEntity.class));
    }

}
