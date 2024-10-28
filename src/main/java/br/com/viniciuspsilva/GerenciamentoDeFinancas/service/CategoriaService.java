package br.com.viniciuspsilva.GerenciamentoDeFinancas.service;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.request.CategoriaFormUpdateDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.domain.Categoria;

public interface CategoriaService {

    Categoria cadastrar(Categoria categoria);

    Categoria buscar(Integer id);

    Iterable<Categoria> listar();

    Categoria atualizar(Integer id, Categoria categoriaAtualizada);
}
