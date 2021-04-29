package br.com.viniciuspsilva.GerenciamentoDeFinancas.service;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.GastoDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.Gasto;

public interface GastoService {
    Gasto cadastrarGasto(Gasto gasto);
    Iterable<Gasto> listarGastos();
    Gasto buscar(Integer id);
}
