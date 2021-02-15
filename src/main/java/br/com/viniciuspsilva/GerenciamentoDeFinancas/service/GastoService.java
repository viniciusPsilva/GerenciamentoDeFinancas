package br.com.viniciuspsilva.GerenciamentoDeFinancas.service;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.GastoDto;

public interface GastoService {
    GastoDto cadastrarGasto(GastoDto gasto);
    Iterable<GastoDto> listarGastos();
}
