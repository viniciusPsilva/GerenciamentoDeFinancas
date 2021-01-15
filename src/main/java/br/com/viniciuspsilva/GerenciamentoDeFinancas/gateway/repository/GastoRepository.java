package br.com.viniciuspsilva.GerenciamentoDeFinancas.gateway.repository;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.Gasto;
import org.springframework.data.repository.CrudRepository;

public interface GastoRepository extends CrudRepository<Gasto, Integer> {
}
