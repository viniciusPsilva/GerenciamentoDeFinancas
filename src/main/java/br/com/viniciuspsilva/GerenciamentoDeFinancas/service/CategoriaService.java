package br.com.viniciuspsilva.GerenciamentoDeFinancas.service;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.CategoriaEntity;

public interface CategoriaService {

    CategoriaEntity cadastrar(CategoriaEntity categoriaEntity);

    CategoriaEntity buscar(Integer id);

}
