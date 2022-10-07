package br.com.viniciuspsilva.GerenciamentoDeFinancas.gateway.repository;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.PlanejamentoMensalDeGastoEntity;
import org.springframework.data.repository.CrudRepository;

public interface PlanoDeGastoRepository extends CrudRepository<PlanejamentoMensalDeGastoEntity, Integer> {
}
