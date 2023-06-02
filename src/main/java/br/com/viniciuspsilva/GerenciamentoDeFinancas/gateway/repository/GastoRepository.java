package br.com.viniciuspsilva.GerenciamentoDeFinancas.gateway.repository;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.GastoEntity;
import org.springframework.data.repository.CrudRepository;

public interface GastoRepository extends CrudRepository<GastoEntity, Integer> {
}
