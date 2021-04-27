package br.com.viniciuspsilva.GerenciamentoDeFinancas.gateway.repository;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.PlanejamentoMensalDeGasto;
import org.springframework.data.repository.CrudRepository;

public interface PlanoDeGastoRepository extends CrudRepository<PlanejamentoMensalDeGasto, Integer> {
}
