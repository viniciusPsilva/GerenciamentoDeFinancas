package br.com.viniciuspsilva.GerenciamentoDeFinancas.service;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.GastoEntity;

public interface GastoService {
    GastoEntity cadastrarGasto(GastoEntity gastoEntity);
    Iterable<GastoEntity> listarGastos();
    GastoEntity buscar(Integer id);
    GastoEntity atualizar(GastoEntity gastoEntity, Integer id);
    void deletar(Integer id);
    GastoEntity atualizarDadosGasto(GastoEntity source, GastoEntity target);
}
