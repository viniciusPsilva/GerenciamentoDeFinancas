package br.com.viniciuspsilva.GerenciamentoDeFinancas.gateway.repository;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.CategoriaEntity;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.PlanoDeGastoEntity;
import org.springframework.data.repository.CrudRepository;

public interface PlanoDeGastoRepository extends CrudRepository<PlanoDeGastoEntity, Integer> {

    PlanoDeGastoEntity findByTituloAndIdUsuario(String titulo, String idUsuario);
}
