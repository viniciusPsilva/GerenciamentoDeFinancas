package br.com.viniciuspsilva.GerenciamentoDeFinancas.gateway.repository;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.Categoria;
import org.springframework.data.repository.CrudRepository;

public interface CategoriaRepository extends CrudRepository<Categoria, Integer> {
}
