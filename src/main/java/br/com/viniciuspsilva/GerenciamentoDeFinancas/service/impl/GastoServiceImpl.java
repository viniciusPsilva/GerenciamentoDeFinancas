package br.com.viniciuspsilva.GerenciamentoDeFinancas.service.impl;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.exception.gasto.GastoNotFoundException;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.gateway.repository.GastoRepository;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.gateway.repository.GastoRepositoryAdapter;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.domain.Gasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.CategoriaEntity;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.GastoEntity;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.PlanejamentoMensalDeGastoEntity;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.mappers.GastoMapper;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.service.CategoriaService;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.service.GastoService;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.service.PlanejamentoMensalDeGastoService;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.visitors.DefinirCategoriaGastoVisitor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class GastoServiceImpl implements GastoService {

    @Autowired
    private GastoRepository repository;

    @Autowired
    private GastoRepositoryAdapter repositoryAdapter;
    @Autowired
    private PlanejamentoMensalDeGastoService planoDeGastoService;
    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private DefinirCategoriaGastoVisitor definirCategoriaGastoVisitor;

    private final GastoMapper gastoMapper = GastoMapper.INSTANCE;

    @Override
    public Gasto cadastrarGasto(final Gasto gasto) {

        gasto.accept(definirCategoriaGastoVisitor);

        //TODO definir plano de gasto com base no nome do plano

        return repositoryAdapter.cadastrar(gasto);
    }

    @Override
    public List<Gasto> listarGastos() {
        return repositoryAdapter.listarGastos();
    }

    @Override
    public Gasto buscar(Integer id) {
       return repositoryAdapter.buscar(id);
    }

    @Override
    public GastoEntity atualizar(GastoEntity gastoAtualizar, Integer id) {
        Gasto gastoEncontrado = buscar(id);
        GastoEntity gastoEntityAtualizado = atualizarDadosGasto(gastoAtualizar, GastoMapper.INSTANCE.mapToEntity(gastoEncontrado));
        return repository.save(gastoEntityAtualizado);

    }

    @Override
    public void deletar(Integer id) {
        repositoryAdapter.deletar(id);
    }

    @Override
    public GastoEntity atualizarDadosGasto(GastoEntity source, GastoEntity target) {
        target.setNome(source.getNome() != null ? source.getNome() : target.getNome());
        target.setDescricao(source.getDescricao() != null ? source.getDescricao() : target.getDescricao());
        target.setValor(source.getValor() != null ? source.getValor() : target.getValor());
        target.setMesReferencia(source.getMesReferencia() != null ? source.getMesReferencia() : target.getMesReferencia());
        target.setDataVencimento(source.getDataVencimento() != null ? source.getDataVencimento() : target.getDataVencimento());
        target.setTipo(source.getTipo() != null ? source.getTipo() : target.getTipo());
        target.setStatus(source.getStatus() != null ? source.getStatus() : target.getStatus());
        target.setPrioridade(source.getPrioridade() != null ? source.getPrioridade() : target.getPrioridade());
        target.setTotalParcelas(source.getTotalParcelas() != null ? source.getTotalParcelas():target.getTotalParcelas());
        target.setParcelaAtual(source.getParcelaAtual() != null ? source.getParcelaAtual():target.getParcelaAtual());

        if (Objects.nonNull(source.getPlanoDeGasto()) && !source.getPlanoDeGasto().getId().equals(target.getPlanoDeGasto().getId())){
            PlanejamentoMensalDeGastoEntity planoDeGasto = planoDeGastoService.buscar(source.getPlanoDeGasto().getId());
            target.setPlanoDeGasto(planoDeGasto);
        }

        if (Objects.nonNull(source.getCategoria()) && !source.getCategoria().getId().equals(target.getCategoria().getId())){
            CategoriaEntity categoriaEntity = categoriaService.buscar(source.getCategoria().getId());
            target.setCategoria(categoriaEntity);
        }

        return target;
    }

}
