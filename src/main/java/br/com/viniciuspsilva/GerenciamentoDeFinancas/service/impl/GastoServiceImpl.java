package br.com.viniciuspsilva.GerenciamentoDeFinancas.service.impl;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.exception.gasto.GastoNotFoundException;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.gateway.repository.GastoRepository;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.GastoDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.Categoria;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.Gasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.PlanejamentoMensalDeGasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enums.MesReferencia;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enums.Prioridade;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enums.StatusGasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enums.Tipo;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.mappers.GastoMapper;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.service.GastoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.swing.table.TableRowSorter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GastoServiceImpl implements GastoService {

    private final GastoRepository repository;

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
        Gasto gastoAtualizado = updateGasto(gasto, gastoEncontrado);
        return repository.save(gastoAtualizado);
    }

    @Override
    public void deletar(Integer id) {
        repository.delete(buscar(id));
    }

    private Gasto updateGasto(Gasto source, Gasto target) {
        target.setNome(source.getNome() != null ? source.getNome() : target.getNome());
        target.setDescricao(source.getDescricao() != null ? source.getDescricao() : target.getDescricao());
        target.setValor(source.getValor() != null ? source.getValor() : target.getValor());
        target.setMesReferencia(source.getMesReferencia() != null ? source.getMesReferencia() : target.getMesReferencia());
        target.setDataVencimento(source.getDataVencimento() != null ? source.getDataVencimento() : target.getDataVencimento());
        target.setTipo(source.getTipo() != null ? source.getTipo() : target.getTipo());
        target.setStatus(source.getStatus() != null ? source.getStatus() : target.getStatus());
        target.setPrioridade(source.getPrioridade() != null ? source.getPrioridade() : target.getPrioridade());
        target.setTotalParcelas(source.getTotalParcelas() != 0 ? source.getTotalParcelas():target.getTotalParcelas());
        target.setParcelaAtual(source.getParcelaAtual() != 0 ? source.getParcelaAtual():target.getParcelaAtual());

        if (Objects.nonNull(source.getPlanoDeGasto())){
            target.getPlanoDeGasto().setId(source.getPlanoDeGasto().getId());
        }

        if (Objects.nonNull(source.getCategoria())){
            target.getCategoria().setId(source.getCategoria().getId());
        }

        return target;
    }

}
