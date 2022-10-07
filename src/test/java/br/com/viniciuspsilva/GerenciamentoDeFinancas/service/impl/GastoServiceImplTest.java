package br.com.viniciuspsilva.GerenciamentoDeFinancas.service.impl;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.exception.gasto.GastoNotFoundException;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.CategoriaEntity;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.GastoEntity;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.gateway.repository.GastoRepository;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.PlanejamentoMensalDeGastoEntity;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.service.CategoriaService;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.service.PlanejamentoMensalDeGastoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
public class GastoServiceImplTest {

    @Mock
    private PlanejamentoMensalDeGastoService planejamentoMensalDeGastoService;

    @Mock
    private  CategoriaService categoriaService;

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
        GastoEntity gastoEntity = Fixture.from(GastoEntity.class).gimme("gasto");

        Mockito.when(repository.save(any(GastoEntity.class))).thenReturn(gastoEntity);

        GastoEntity gastoEntityPersistido = gastoService.cadastrarGasto(gastoEntity);

        assertNotNull(gastoEntityPersistido);
        assertEquals(gastoEntity.getNome(), gastoEntityPersistido.getNome());
        assertEquals(gastoEntity.getDescricao(), gastoEntityPersistido.getDescricao());
        assertEquals(gastoEntity.getValor(), gastoEntityPersistido.getValor());
        assertEquals(gastoEntity.getTipo(), gastoEntityPersistido.getTipo());
        assertEquals(gastoEntity.getStatus(), gastoEntityPersistido.getStatus());
        assertEquals(gastoEntity.getPrioridade(), gastoEntityPersistido.getPrioridade());
    }


    @Test
    public void DeveLitarGatos() {
        GastoEntity gastoEntity = Fixture.from(GastoEntity.class).gimme("gasto");
        Mockito.when(repository.findAll()).thenReturn(Collections.singletonList(gastoEntity));

        Iterable<GastoEntity> gastos = gastoService.listarGastos();

        gastos.forEach(g -> {
            assertEquals(gastoEntity.getId(), g.getId());
            assertEquals(gastoEntity.getNome(), g.getNome());
            assertEquals(gastoEntity.getDescricao(), g.getDescricao());
            assertEquals(gastoEntity.getValor(), g.getValor());
            assertEquals(gastoEntity.getTipo(), g.getTipo());
            assertEquals(gastoEntity.getStatus(), g.getStatus());
            assertEquals(gastoEntity.getPrioridade(), g.getPrioridade());
            assertEquals(gastoEntity.getMesReferencia(), g.getMesReferencia());
            assertEquals(gastoEntity.getDataVencimento(), g.getDataVencimento());
            assertEquals(gastoEntity.getTotalParcelas(), g.getTotalParcelas());
            assertEquals(gastoEntity.getParcelaAtual(), g.getParcelaAtual());
            assertEquals(gastoEntity.getDataCriacao(), g.getDataCriacao());

        });
    }

    @Test
    public void deveBuscarUmGastoApartirDeUmId() {

        GastoEntity gastoEntity = Fixture.from(GastoEntity.class).gimme("gasto");
        Mockito.when(repository.findById(any(Integer.class))).thenReturn(Optional.of(gastoEntity));

        GastoEntity gastoEntityEncontrado = gastoService.buscar(1);

        assertEquals(gastoEntity.getId(), gastoEntityEncontrado.getId());
        assertEquals(gastoEntity.getNome(), gastoEntityEncontrado.getNome());
        assertEquals(gastoEntity.getDescricao(), gastoEntityEncontrado.getDescricao());
        assertEquals(gastoEntity.getValor(), gastoEntityEncontrado.getValor());
        assertEquals(gastoEntity.getTipo(), gastoEntityEncontrado.getTipo());
        assertEquals(gastoEntity.getStatus(), gastoEntityEncontrado.getStatus());
        assertEquals(gastoEntity.getPrioridade(), gastoEntityEncontrado.getPrioridade());
        assertEquals(gastoEntity.getMesReferencia(), gastoEntityEncontrado.getMesReferencia());
        assertEquals(gastoEntity.getDataVencimento(), gastoEntityEncontrado.getDataVencimento());
        assertEquals(gastoEntity.getTotalParcelas(), gastoEntityEncontrado.getTotalParcelas());
        assertEquals(gastoEntity.getParcelaAtual(), gastoEntityEncontrado.getParcelaAtual());
        assertEquals(gastoEntity.getDataCriacao(), gastoEntityEncontrado.getDataCriacao());
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

    @Test
    public void deveDeletarUmGastoPorId(){
        Mockito.doNothing().when(repository).deleteById(any(Integer.class));

        final Integer id = 1;
        repository.deleteById(id);

        Mockito.verify(repository, Mockito.times(1)).deleteById(any(Integer.class));
    }

    @Test
    public void deveAtualizarDadosDeUmGastoComBaseEmOutroGasto(){

        PlanejamentoMensalDeGastoEntity planejamentoMensalDeGastoEntity = Fixture.from(PlanejamentoMensalDeGastoEntity.class).gimme("OK");
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

    @Test
    public void deveAtualizarDadosDePlanejamentoMensalDeUmGasto(){

        PlanejamentoMensalDeGastoEntity planejamentoMensalDeGastoEntity = Fixture.from(PlanejamentoMensalDeGastoEntity.class).gimme("OK");
        PlanejamentoMensalDeGastoEntity planejamentoMensalDeGastoEntityTarget = Fixture.from(PlanejamentoMensalDeGastoEntity.class).gimme("OK_id_2");
        Mockito.when(planejamentoMensalDeGastoService.buscar(any(Integer.class))).thenReturn(planejamentoMensalDeGastoEntity);


        GastoEntity source = Fixture.from(GastoEntity.class).gimme("gasto");
        GastoEntity target = Fixture.from(GastoEntity.class).gimme("target");
        target.setPlanoDeGasto(planejamentoMensalDeGastoEntityTarget);

        GastoEntity gastoEntityAtualizado = gastoService.atualizarDadosGasto(source, target);

        PlanejamentoMensalDeGastoEntity planoDeGastoAtualizado = gastoEntityAtualizado.getPlanoDeGasto();
        PlanejamentoMensalDeGastoEntity planoDeGastoEsperado = source.getPlanoDeGasto();

        assertNotNull(planoDeGastoAtualizado);
        assertNotNull(planoDeGastoEsperado);
        assertEquals(planoDeGastoEsperado.getId(), planoDeGastoAtualizado.getId());
        assertEquals(planoDeGastoEsperado.getTitulo(), planoDeGastoAtualizado.getTitulo());
        assertEquals(planoDeGastoEsperado.getDescricao(), planoDeGastoAtualizado.getDescricao());
        assertEquals(planoDeGastoEsperado.getMesReferencia(), planoDeGastoAtualizado.getMesReferencia());
        assertEquals(planoDeGastoEsperado.getStatus(), planoDeGastoAtualizado.getStatus());
        assertEquals(planoDeGastoEsperado.getIdUsuario(), planoDeGastoAtualizado.getIdUsuario());
        assertEquals(planoDeGastoEsperado.getValorPlanejado(), planoDeGastoAtualizado.getValorPlanejado());
        assertEquals(planoDeGastoEsperado.getDataCriacao(), planoDeGastoAtualizado.getDataCriacao());

        Mockito.verify(planejamentoMensalDeGastoService, Mockito.times(1)).buscar(any(Integer.class));
    }




}
