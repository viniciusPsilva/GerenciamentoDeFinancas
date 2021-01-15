package br.com.viniciuspsilva.GerenciamentoDeFinancas.gateway.repository;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.PlanoDeGasto;
import org.springframework.data.repository.CrudRepository;

public interface PlanoDeGastoRepository extends CrudRepository<PlanoDeGasto, Integer> {
}
