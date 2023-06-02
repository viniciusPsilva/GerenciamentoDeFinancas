package br.com.viniciuspsilva.GerenciamentoDeFinancas.service;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.domain.PlanejamentoMensalDeGasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.PlanejamentoMensalDeGastoEntity;

public interface PlanejamentoMensalDeGastoService {

    PlanejamentoMensalDeGastoEntity buscar(Integer id);
    PlanejamentoMensalDeGasto cadastrar(PlanejamentoMensalDeGasto plano);

}
