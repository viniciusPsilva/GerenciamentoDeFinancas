package br.com.viniciuspsilva.GerenciamentoDeFinancas.service.impl;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.exception.categoria.CategoriaNotFoundException;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.gateway.repository.CategoriaRepository;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.Categoria;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    @Override
    public Categoria cadastrar(Categoria categoria)
    {
        return repository.save(categoria);
    }

    @Override
    public Categoria buscar(Integer id) {
        return repository.findById(id).orElseThrow(() -> new CategoriaNotFoundException("Não foi Possível encontrar a categoria id: " + id));
    }
}
