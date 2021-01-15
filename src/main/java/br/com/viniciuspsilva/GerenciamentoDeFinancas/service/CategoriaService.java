package br.com.viniciuspsilva.GerenciamentoDeFinancas.service;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.CategoriaDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.Categoria;

import javax.persistence.criteria.CriteriaBuilder;

public interface CategoriaService {

    CategoriaDto cadastrar(CategoriaDto categoriaDto);

    Categoria buscar(Integer id);

}
