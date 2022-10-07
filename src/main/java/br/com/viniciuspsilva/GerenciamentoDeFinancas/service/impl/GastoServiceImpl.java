package br.com.viniciuspsilva.GerenciamentoDeFinancas.service.impl;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.exception.gasto.GastoNotFoundException;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.gateway.repository.GastoRepository;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.CategoriaEntity;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.GastoEntity;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.PlanejamentoMensalDeGastoEntity;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.mappers.GastoMapper;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.service.CategoriaService;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.service.GastoService;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.service.PlanejamentoMensalDeGastoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GastoServiceImpl implements GastoService {

    private final GastoRepository repository;

    private final PlanejamentoMensalDeGastoService planoDeGastoService;

    private final CategoriaService categoriaService;

    private final GastoMapper gastoMapper = GastoMapper.INSTANCE;

    @Override
    public GastoEntity cadastrarGasto(final GastoEntity gastoEntity) {
        return repository.save(gastoEntity);
    }

    @Override
    public Iterable<GastoEntity> listarGastos() {
        return repository.findAll();
    }

    @Override
    public GastoEntity buscar(Integer id) {
        Optional<GastoEntity> gasto = repository.findById(id);

        if (gasto.isEmpty()) {
            throw new GastoNotFoundException("O gasto com id informado não foi encontrado");
        }

        return gasto.get();
    }

    @Override
    public GastoEntity atualizar(GastoEntity gastoEntity, Integer id) {
        GastoEntity gastoEntityEncontrado = buscar(id);
        GastoEntity gastoEntityAtualizado = atualizarDadosGasto(gastoEntity, gastoEntityEncontrado);
        return repository.save(gastoEntityAtualizado);
    }

    @Override
    public void deletar(Integer id) {
        repository.delete(buscar(id));
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
