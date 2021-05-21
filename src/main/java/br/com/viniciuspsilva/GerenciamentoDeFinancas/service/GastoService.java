package br.com.viniciuspsilva.GerenciamentoDeFinancas.service;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.Gasto;

public interface GastoService {
    Gasto cadastrarGasto(Gasto gasto);
    Iterable<Gasto> listarGastos();
    Gasto buscar(Integer id);
    Gasto atualizar(Gasto gasto, Integer id);
    void deletar(Integer id);
    Gasto atualizarDadosGasto(Gasto source, Gasto target);
}
