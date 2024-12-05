package br.com.viniciuspsilva.GerenciamentoDeFinancas.service;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.domain.PlanoDeGasto;

public interface PlanoDeGastoService {

    PlanoDeGasto buscar(final Integer id);
    PlanoDeGasto cadastrar(PlanoDeGasto plano);

    Iterable<PlanoDeGasto> listar();

    PlanoDeGasto atualizar(final String id, PlanoDeGasto updated);

    void deletar(final Integer id);
}
