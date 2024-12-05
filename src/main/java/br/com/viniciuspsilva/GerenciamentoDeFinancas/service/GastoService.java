package br.com.viniciuspsilva.GerenciamentoDeFinancas.service;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.domain.Gasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.GastoEntity;

import java.util.List;

public interface GastoService {
    Gasto cadastrarGasto(Gasto gasto);
    List<Gasto> listarGastos();
    Gasto buscar(Integer id);
    GastoEntity atualizar(GastoEntity gastoEntity, Integer id);
    void deletar(Integer id);
    GastoEntity atualizarDadosGasto(GastoEntity source, GastoEntity target);
}
