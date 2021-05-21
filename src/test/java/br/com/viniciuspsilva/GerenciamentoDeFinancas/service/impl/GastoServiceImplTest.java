package br.com.viniciuspsilva.GerenciamentoDeFinancas.service.impl;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.exception.gasto.GastoNotFoundException;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.Categoria;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.Gasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.gateway.repository.GastoRepository;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.PlanejamentoMensalDeGasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.service.CategoriaService;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.service.PlanejamentoMensalDeGastoService;
import org.junit.jupiter.api.Assertions;
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
        Gasto gasto = Fixture.from(Gasto.class).gimme("gasto");

        Mockito.when(repository.save(any(Gasto.class))).thenReturn(gasto);

        Gasto gastoPersistido = gastoService.cadastrarGasto(gasto);

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

        Iterable<Gasto> gastos = gastoService.listarGastos();

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

        Gasto gasto = Fixture.from(Gasto.class).gimme("gasto");
        Mockito.when(repository.findById(any(Integer.class))).thenReturn(Optional.of(gasto));

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

        PlanejamentoMensalDeGasto planejamentoMensalDeGasto = Fixture.from(PlanejamentoMensalDeGasto.class).gimme("OK");
        Mockito.when(planejamentoMensalDeGastoService.buscar(any(Integer.class))).thenReturn(planejamentoMensalDeGasto);

        Categoria categoria = Fixture.from(Categoria.class).gimme("valid");
        Mockito.when(categoriaService.buscar(any(Integer.class))).thenReturn(categoria);


        Gasto source = Fixture.from(Gasto.class).gimme("gasto");
        Gasto target = Fixture.from(Gasto.class).gimme("target");

        Gasto gastoAtualizado = gastoService.atualizarDadosGasto(source, target);

        assertEquals(source.getId(), gastoAtualizado.getId());
        assertEquals(source.getNome(), gastoAtualizado.getNome());
        assertEquals(source.getDescricao(), gastoAtualizado.getDescricao());
        assertEquals(source.getValor(), gastoAtualizado.getValor());
        assertEquals(source.getTipo(), gastoAtualizado.getTipo());
        assertEquals(source.getStatus(), gastoAtualizado.getStatus());
        assertEquals(source.getPrioridade(), gastoAtualizado.getPrioridade());
        assertEquals(source.getMesReferencia(), gastoAtualizado.getMesReferencia());
        assertEquals(source.getDataVencimento(), gastoAtualizado.getDataVencimento());
        assertEquals(source.getTotalParcelas(), gastoAtualizado.getTotalParcelas());
        assertEquals(source.getParcelaAtual(), gastoAtualizado.getParcelaAtual());
        assertEquals(source.getDataCriacao(), gastoAtualizado.getDataCriacao());

        Mockito.verify(categoriaService, Mockito.never()).buscar(any(Integer.class));
        Mockito.verify(planejamentoMensalDeGastoService, Mockito.never()).buscar(any(Integer.class));
    }

    @Test
    public void deveAtualizarDadosDeCategoriaDeUmGasto(){
        Categoria categoria = Fixture.from(Categoria.class).gimme("valid");
        Categoria categoriaTarget = Fixture.from(Categoria.class).gimme("valid_id_2");
        Mockito.when(categoriaService.buscar(any(Integer.class))).thenReturn(categoria);


        Gasto source = Fixture.from(Gasto.class).gimme("gasto");

        Gasto target = Fixture.from(Gasto.class).gimme("target");
        target.setCategoria(categoriaTarget);

        Gasto gastoAtualizado = gastoService.atualizarDadosGasto(source, target);

        Categoria categoriaExperada = source.getCategoria();
        Categoria categoriaAtualizada = gastoAtualizado.getCategoria();

        assertNotNull(categoriaExperada);
        assertNotNull(categoriaAtualizada);

        assertEquals(categoriaExperada.getId(), categoriaAtualizada.getId());
        assertEquals(categoriaExperada.getNome(), categoriaAtualizada.getNome());
        assertEquals(categoriaExperada.getDescricao(), categoriaAtualizada.getDescricao());
        assertEquals(categoriaExperada.getDataCriacao(), categoriaAtualizada.getDataCriacao());
        Mockito.verify(categoriaService, Mockito.times(1)).buscar(any(Integer.class));

    }

    @Test
    public void deveAtualizarDadosDePlanejamentoMensalDeUmGasto(){

        PlanejamentoMensalDeGasto planejamentoMensalDeGasto = Fixture.from(PlanejamentoMensalDeGasto.class).gimme("OK");
        PlanejamentoMensalDeGasto planejamentoMensalDeGastoTarget = Fixture.from(PlanejamentoMensalDeGasto.class).gimme("OK_id_2");
        Mockito.when(planejamentoMensalDeGastoService.buscar(any(Integer.class))).thenReturn(planejamentoMensalDeGasto);


        Gasto source = Fixture.from(Gasto.class).gimme("gasto");
        Gasto target = Fixture.from(Gasto.class).gimme("target");
        target.setPlanoDeGasto(planejamentoMensalDeGastoTarget);

        Gasto gastoAtualizado = gastoService.atualizarDadosGasto(source, target);

        PlanejamentoMensalDeGasto planoDeGastoAtualizado = gastoAtualizado.getPlanoDeGasto();
        PlanejamentoMensalDeGasto planoDeGastoEsperado = source.getPlanoDeGasto();

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
