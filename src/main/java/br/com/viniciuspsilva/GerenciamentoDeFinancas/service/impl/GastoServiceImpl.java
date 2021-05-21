package br.com.viniciuspsilva.GerenciamentoDeFinancas.service.impl;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.exception.gasto.GastoNotFoundException;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.gateway.repository.GastoRepository;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.Categoria;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.Gasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.PlanejamentoMensalDeGasto;
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
    public Gasto cadastrarGasto(final Gasto gasto) {
        return repository.save(gasto);
    }

    @Override
    public Iterable<Gasto> listarGastos() {
        return repository.findAll();
    }

    @Override
    public Gasto buscar(Integer id) {
        Optional<Gasto> gasto = repository.findById(id);

        if (gasto.isEmpty()) {
            throw new GastoNotFoundException("O gasto com id informado não foi encontrado");
        }

        return gasto.get();
    }

    @Override
    public Gasto atualizar(Gasto gasto, Integer id) {
        Gasto gastoEncontrado = buscar(id);
        Gasto gastoAtualizado = atualizarDadosGasto(gasto, gastoEncontrado);
        return repository.save(gastoAtualizado);
    }

    @Override
    public void deletar(Integer id) {
        repository.delete(buscar(id));
    }

    @Override
    public Gasto atualizarDadosGasto(Gasto source, Gasto target) {
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
            PlanejamentoMensalDeGasto planoDeGasto = planoDeGastoService.buscar(source.getPlanoDeGasto().getId());
            target.setPlanoDeGasto(planoDeGasto);
        }

        if (Objects.nonNull(source.getCategoria()) && !source.getCategoria().getId().equals(target.getCategoria().getId())){
            Categoria categoria = categoriaService.buscar(source.getCategoria().getId());
            target.setCategoria(categoria);
        }

        return target;
    }

}
