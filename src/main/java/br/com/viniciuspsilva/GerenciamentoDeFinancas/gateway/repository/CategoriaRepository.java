package br.com.viniciuspsilva.GerenciamentoDeFinancas.gateway.repository;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.CategoriaEntity;
import org.springframework.data.repository.CrudRepository;

public interface CategoriaRepository extends CrudRepository<CategoriaEntity, Integer> {
}
