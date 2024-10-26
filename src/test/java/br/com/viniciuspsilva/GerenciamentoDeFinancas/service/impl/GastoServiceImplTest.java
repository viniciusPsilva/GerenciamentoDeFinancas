package br.com.viniciuspsilva.GerenciamentoDeFinancas.service.impl;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.exception.gasto.GastoNotFoundException;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.gateway.repository.GastoRepositoryAdapter;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.domain.Gasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.domain.PlanoDeGasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.CategoriaEntity;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.GastoEntity;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.gateway.repository.GastoRepository;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.PlanoDeGastoEntity;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.service.CategoriaService;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.service.PlanoDeGastoService;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.visitors.DefinirCategoriaGastoVisitor;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
public class GastoServiceImplTest {

    @Mock
    private PlanoDeGastoService planejamentoMensalDeGastoService;

    @Mock
    private  CategoriaService categoriaService;

    @Mock
    private GastoRepository repository;
    @Mock
    private GastoRepositoryAdapter repositoryAdapter;

    @Mock
    private DefinirCategoriaGastoVisitor definirCategoriaGastoVisitor;

    @InjectMocks
    private GastoServiceImpl gastoService;

    @BeforeEach
    public void setUp() {
        FixtureFactoryLoader.loadTemplates("br.com.viniciuspsilva.GerenciamentoDeFinancas.template");
    }

    @Test
    public void cadastrarGasto() {
        Gasto gasto = Fixture.from(Gasto.class).gimme("valid");

        Mockito.when(repositoryAdapter.cadastrar(any(Gasto.class))).thenReturn(gasto);
        Mockito.when(definirCategoriaGastoVisitor.visit(any(Gasto.class))).thenReturn(gasto);

        Gasto gastoEntityPersistido = gastoService.cadastrarGasto(gasto);

        assertNotNull(gastoEntityPersistido);
        assertEquals(gasto.getNome(), gastoEntityPersistido.getNome());
        assertEquals(gasto.getDescricao(), gastoEntityPersistido.getDescricao());
        assertEquals(gasto.getValor(), gastoEntityPersistido.getValor());
        assertEquals(gasto.getTipo(), gastoEntityPersistido.getTipo());
        assertEquals(gasto.getStatus(), gastoEntityPersistido.getStatus());
        assertEquals(gasto.getPrioridade(), gastoEntityPersistido.getPrioridade());
    }


    @Test
    public void deveLitarGatos() {
        Gasto gasto = Fixture.from(Gasto.class).gimme("valid");
        Mockito.when(repositoryAdapter.listarGastos()).thenReturn(Collections.singletonList(gasto));

        List<Gasto> gastos = gastoService.listarGastos();

        gastos.forEach(g -> {
            assertEquals(gasto.getId(), g.getId());
            assertEquals(gasto.getNome(), g.getNome());
            assertEquals(gasto.getDescricao(), g.getDescricao());
            assertEquals(gasto.getValor(), g.getValor());
            assertEquals(gasto.getTipo(), g.getTipo());
            assertEquals(gasto.getStatus(), g.getStatus());
            assertEquals(gasto.getPrioridade(), g.getPrioridade());
            assertEquals(gasto.getMesReferencia(), g.getMesReferencia());
            assertEquals(gasto.getDataVencimento(), g.getDataVencimento());
            assertEquals(gasto.getTotalParcelas(), g.getTotalParcelas());
            assertEquals(gasto.getParcelaAtual(), g.getParcelaAtual());
            assertEquals(gasto.getDataCriacao(), g.getDataCriacao());

        });
    }

    @Test
    public void deveBuscarUmGastoApartirDeUmId() {

        Gasto gasto = Fixture.from(Gasto.class).gimme("valid");
        Mockito.when(repositoryAdapter.buscar(any(Integer.class))).thenReturn(gasto);

        Gasto gastoEncontrado = gastoService.buscar(1);

        assertEquals(gasto.getId(), gastoEncontrado.getId());
        assertEquals(gasto.getNome(), gastoEncontrado.getNome());
        assertEquals(gasto.getDescricao(), gastoEncontrado.getDescricao());
        assertEquals(gasto.getValor(), gastoEncontrado.getValor());
        assertEquals(gasto.getTipo(), gastoEncontrado.getTipo());
        assertEquals(gasto.getStatus(), gastoEncontrado.getStatus());
        assertEquals(gasto.getPrioridade(), gastoEncontrado.getPrioridade());
        assertEquals(gasto.getMesReferencia(), gastoEncontrado.getMesReferencia());
        assertEquals(gasto.getDataVencimento(), gastoEncontrado.getDataVencimento());
        assertEquals(gasto.getTotalParcelas(), gastoEncontrado.getTotalParcelas());
        assertEquals(gasto.getParcelaAtual(), gastoEncontrado.getParcelaAtual());
        assertEquals(gasto.getDataCriacao(), gastoEncontrado.getDataCriacao());
    }

    @Test
    public void deveRetornarGastoNotFoundExceptionQuandoGastoNaoExistir(){

        final String expectedErrorMessage = "O gasto com id informado não foi encontrado";

        Mockito.when(repositoryAdapter.buscar(any(Integer.class))).thenThrow(new GastoNotFoundException(expectedErrorMessage));

        GastoNotFoundException ex = assertThrows(GastoNotFoundException.class, () -> {
            gastoService.buscar(1);
        });

        assertEquals(expectedErrorMessage, ex.getMessage());

    }

    @Test
    public void deveDeletarUmGastoPorId(){
        Mockito.doNothing().when(repositoryAdapter).deletar(any(Integer.class));

        final Integer id = 1;
        repository.deleteById(id);

        Mockito.verify(repository, Mockito.times(1)).deleteById(any(Integer.class));
    }

    //TODO concertar atualização de gastos
    @Ignore
    public void deveAtualizarDadosDeUmGastoComBaseEmOutroGasto(){

        PlanoDeGasto planejamentoMensalDeGastoEntity = Fixture.from(PlanoDeGasto.class).gimme("OK");
        Mockito.when(planejamentoMensalDeGastoService.buscar(any(Integer.class))).thenReturn(planejamentoMensalDeGastoEntity);

        CategoriaEntity categoriaEntity = Fixture.from(CategoriaEntity.class).gimme("valid");
        Mockito.when(categoriaService.buscar(any(Integer.class))).thenReturn(categoriaEntity);


        GastoEntity source = Fixture.from(GastoEntity.class).gimme("gasto");
        GastoEntity target = Fixture.from(GastoEntity.class).gimme("target");

        GastoEntity gastoEntityAtualizado = gastoService.atualizarDadosGasto(source, target);

        assertEquals(source.getId(), gastoEntityAtualizado.getId());
        assertEquals(source.getNome(), gastoEntityAtualizado.getNome());
        assertEquals(source.getDescricao(), gastoEntityAtualizado.getDescricao());
        assertEquals(source.getValor(), gastoEntityAtualizado.getValor());
        assertEquals(source.getTipo(), gastoEntityAtualizado.getTipo());
        assertEquals(source.getStatus(), gastoEntityAtualizado.getStatus());
        assertEquals(source.getPrioridade(), gastoEntityAtualizado.getPrioridade());
        assertEquals(source.getMesReferencia(), gastoEntityAtualizado.getMesReferencia());
        assertEquals(source.getDataVencimento(), gastoEntityAtualizado.getDataVencimento());
        assertEquals(source.getTotalParcelas(), gastoEntityAtualizado.getTotalParcelas());
        assertEquals(source.getParcelaAtual(), gastoEntityAtualizado.getParcelaAtual());
        assertEquals(source.getDataCriacao(), gastoEntityAtualizado.getDataCriacao());

        Mockito.verify(categoriaService, Mockito.never()).buscar(any(Integer.class));
        Mockito.verify(planejamentoMensalDeGastoService, Mockito.never()).buscar(any(Integer.class));
    }

    @Test
    public void deveAtualizarDadosDeCategoriaDeUmGasto(){
        CategoriaEntity categoriaEntity = Fixture.from(CategoriaEntity.class).gimme("valid");
        CategoriaEntity categoriaEntityTarget = Fixture.from(CategoriaEntity.class).gimme("valid_id_2");
        Mockito.when(categoriaService.buscar(any(Integer.class))).thenReturn(categoriaEntity);


        GastoEntity source = Fixture.from(GastoEntity.class).gimme("gasto");

        GastoEntity target = Fixture.from(GastoEntity.class).gimme("target");
        target.setCategoria(categoriaEntityTarget);

        GastoEntity gastoEntityAtualizado = gastoService.atualizarDadosGasto(source, target);

        CategoriaEntity categoriaEntityExperada = source.getCategoria();
        CategoriaEntity categoriaEntityAtualizada = gastoEntityAtualizado.getCategoria();

        assertNotNull(categoriaEntityExperada);
        assertNotNull(categoriaEntityAtualizada);

        assertEquals(categoriaEntityExperada.getId(), categoriaEntityAtualizada.getId());
        assertEquals(categoriaEntityExperada.getNome(), categoriaEntityAtualizada.getNome());
        assertEquals(categoriaEntityExperada.getDescricao(), categoriaEntityAtualizada.getDescricao());
        assertEquals(categoriaEntityExperada.getDataCriacao(), categoriaEntityAtualizada.getDataCriacao());
        Mockito.verify(categoriaService, Mockito.times(1)).buscar(any(Integer.class));

    }

    @Ignore
    //TODO concertar atualização de gastos
    public void deveAtualizarDadosDePlanejamentoMensalDeUmGasto(){

        PlanoDeGasto planoDeGasto = Fixture.from(PlanoDeGasto.class).gimme("OK");
        PlanoDeGastoEntity planejamentoMensalDeGastoEntityTarget = Fixture.from(PlanoDeGastoEntity.class).gimme("OK_id_2");
        Mockito.when(planejamentoMensalDeGastoService.buscar(any(Integer.class))).thenReturn(planoDeGasto);


        GastoEntity source = Fixture.from(GastoEntity.class).gimme("gasto");
        GastoEntity target = Fixture.from(GastoEntity.class).gimme("target");
        target.setPlanoDeGasto(planejamentoMensalDeGastoEntityTarget);

        GastoEntity gastoEntityAtualizado = gastoService.atualizarDadosGasto(source, target);

        PlanoDeGastoEntity planoDeGastoAtualizado = gastoEntityAtualizado.getPlanoDeGasto();
        PlanoDeGastoEntity planoDeGastoEsperado = source.getPlanoDeGasto();

        assertNotNull(planoDeGastoAtualizado);
        assertNotNull(planoDeGastoEsperado);
        assertEquals(planoDeGastoEsperado.getId(), planoDeGastoAtualizado.getId());
        assertEquals(planoDeGastoEsperado.getTitulo(), planoDeGastoAtualizado.getTitulo());
        assertEquals(planoDeGastoEsperado.getDescricao(), planoDeGastoAtualizado.getDescricao());
        assertEquals(planoDeGastoEsperado.getStatus(), planoDeGastoAtualizado.getStatus());
        assertEquals(planoDeGastoEsperado.getIdUsuario(), planoDeGastoAtualizado.getIdUsuario());
        assertEquals(planoDeGastoEsperado.getValorPlanejado(), planoDeGastoAtualizado.getValorPlanejado());
        assertEquals(planoDeGastoEsperado.getDataCriacao(), planoDeGastoAtualizado.getDataCriacao());

        Mockito.verify(planejamentoMensalDeGastoService, Mockito.times(1)).buscar(any(Integer.class));
    }




}
