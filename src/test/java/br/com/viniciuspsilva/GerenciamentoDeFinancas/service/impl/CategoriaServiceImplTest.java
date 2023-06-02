package br.com.viniciuspsilva.GerenciamentoDeFinancas.service.impl;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.exception.categoria.CategoriaException;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.exception.categoria.CategoriaNotFoundException;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.gateway.repository.CategoriaRepository;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.CategoriaEntity;
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
        CategoriaEntity categoriaEntity = Fixture.from(CategoriaEntity.class).gimme("valid");
        Mockito.when(repository.save(any(CategoriaEntity.class))).thenReturn(categoriaEntity);

        CategoriaEntity categoriaEntityCadastrada = service.cadastrar(categoriaEntity);

        Assertions.assertNotNull(categoriaEntityCadastrada);
        Assertions.assertEquals(categoriaEntity.getId(), categoriaEntityCadastrada.getId());
        Assertions.assertEquals(categoriaEntity.getDescricao(), categoriaEntityCadastrada.getDescricao());
        Assertions.assertEquals(categoriaEntity.getNome(), categoriaEntityCadastrada.getNome());
        Assertions.assertEquals(categoriaEntity.getDataCriacao(), categoriaEntityCadastrada.getDataCriacao());
    }

    @Test
    public void deveLancarCategoriaExceptionAoCadastrarCategoria(){
        CategoriaEntity categoriaEntity = Fixture.from(CategoriaEntity.class).gimme("valid");
        final String expectedException = "Erro ao cadastrar Categoria";

        Mockito.when(repository.save(any(CategoriaEntity.class))).thenThrow(new RuntimeException());

        CategoriaException categoriaException = Assertions.assertThrows(CategoriaException.class, () -> {
            service.cadastrar(categoriaEntity);
        });

        Assertions.assertNotNull(categoriaException);
        Assertions.assertSame(categoriaException.getClass(), CategoriaException.class);
        Assertions.assertEquals(expectedException, categoriaException.getMessage());

    }

    @Test
    public void deveBuscarCategoriaPorId(){
        CategoriaEntity categoriaEntity = Fixture.from(CategoriaEntity.class).gimme("valid");
        Mockito.when(repository.findById(anyInt())).thenReturn(Optional.of(categoriaEntity));

        CategoriaEntity categoriaEntityEncontrada = service.buscar(1);

        Assertions.assertNotNull(categoriaEntityEncontrada);
        Assertions.assertEquals(categoriaEntity.getId(), categoriaEntityEncontrada.getId());
        Assertions.assertEquals(categoriaEntity.getDescricao(), categoriaEntityEncontrada.getDescricao());
        Assertions.assertEquals(categoriaEntity.getNome(), categoriaEntityEncontrada.getNome());
        Assertions.assertEquals(categoriaEntity.getDataCriacao(), categoriaEntityEncontrada.getDataCriacao());
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
