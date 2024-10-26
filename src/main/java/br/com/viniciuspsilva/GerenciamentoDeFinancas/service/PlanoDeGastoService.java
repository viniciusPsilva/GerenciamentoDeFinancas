package br.com.viniciuspsilva.GerenciamentoDeFinancas.service;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.domain.PlanoDeGasto;

public interface PlanoDeGastoService {

    PlanoDeGasto buscar(Integer id);
    PlanoDeGasto cadastrar(PlanoDeGasto plano);

    Iterable<PlanoDeGasto> listar();


}
