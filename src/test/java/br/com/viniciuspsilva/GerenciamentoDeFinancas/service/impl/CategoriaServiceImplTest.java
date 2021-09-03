package br.com.viniciuspsilva.GerenciamentoDeFinancas.service.impl;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.exception.categoria.CategoriaException;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.exception.categoria.CategoriaNotFoundException;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.gateway.repository.CategoriaRepository;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.Categoria;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;

@ExtendWith(SpringExtension.class)
public class CategoriaServiceImplTest {

    @InjectMocks
    public CategoriaServiceImpl service;

    @Mock
    private CategoriaRepository repository;

    @BeforeEach
    public void setUp() {
        FixtureFactoryLoader.loadTemplates("br.com.viniciuspsilva.GerenciamentoDeFinancas.template");
    }

    @Test
    public void deveCadastrarCategoria(){
        Categoria categoria = Fixture.from(Categoria.class).gimme("valid");
        Mockito.when(repository.save(any(Categoria.class))).thenReturn(categoria);

        Categoria categoriaCadastrada = service.cadastrar(categoria);

        Assertions.assertNotNull(categoriaCadastrada);
        Assertions.assertEquals(categoria.getId(), categoriaCadastrada.getId());
        Assertions.assertEquals(categoria.getDescricao(), categoriaCadastrada.getDescricao());
        Assertions.assertEquals(categoria.getNome(), categoriaCadastrada.getNome());
        Assertions.assertEquals(categoria.getDataCriacao(), categoriaCadastrada.getDataCriacao());
    }

    @Test
    public void deveLancarCategoriaExceptionAoCadastrarCategoria(){
        Categoria categoria = Fixture.from(Categoria.class).gimme("valid");
        final String expectedException = "Erro ao cadastrar Categoria";

        Mockito.when(repository.save(any(Categoria.class))).thenThrow(new RuntimeException());

        CategoriaException categoriaException = Assertions.assertThrows(CategoriaException.class, () -> {
            service.cadastrar(categoria);
        });

        Assertions.assertNotNull(categoriaException);
        Assertions.assertSame(categoriaException.getClass(), CategoriaException.class);
        Assertions.assertEquals(expectedException, categoriaException.getMessage());

    }

    @Test
    public void deveBuscarCategoriaPorId(){
        Categoria categoria = Fixture.from(Categoria.class).gimme("valid");
        Mockito.when(repository.findById(anyInt())).thenReturn(Optional.of(categoria));

        Categoria categoriaEncontrada = service.buscar(1);

        Assertions.assertNotNull(categoriaEncontrada);
        Assertions.assertEquals(categoria.getId(), categoriaEncontrada.getId());
        Assertions.assertEquals(categoria.getDescricao(), categoriaEncontrada.getDescricao());
        Assertions.assertEquals(categoria.getNome(), categoriaEncontrada.getNome());
        Assertions.assertEquals(categoria.getDataCriacao(), categoriaEncontrada.getDataCriacao());
    }

    @Test
    public void deveLancarCategoriaNotFoundExceptionQuandoBuscarCategoriaPorId(){
        final String expectedException = "Não foi Possível encontrar a categoria id: 1";
        Mockito.when(repository.findById(anyInt())).thenReturn(Optional.empty());

        CategoriaNotFoundException exception = Assertions.assertThrows(CategoriaNotFoundException.class, () -> {
            service.buscar(1);
        });

        Assertions.assertNotNull(exception);
        Assertions.assertSame(exception.getClass(), CategoriaNotFoundException.class);
        Assertions.assertEquals(expectedException, exception.getMessage());
    }

    @Test
    public void deveLancarCategoriaExceptionQuandoBuscarCategoriaPorId(){
        final String expectedException = "Erro ao buscar categoria por id";
        Mockito.when(repository.findById(anyInt())).thenThrow(new RuntimeException());

        CategoriaException exception = Assertions.assertThrows(CategoriaException.class, () -> {
            service.buscar(1);
        });

        Assertions.assertNotNull(exception);
        Assertions.assertSame(exception.getClass(), CategoriaException.class);
        Assertions.assertEquals(expectedException, exception.getMessage());
    }

}
