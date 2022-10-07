package br.com.viniciuspsilva.GerenciamentoDeFinancas.service.impl;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.exception.categoria.CategoriaException;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.exception.categoria.CategoriaNotFoundException;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.gateway.repository.CategoriaRepository;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.CategoriaEntity;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    @Override
    public CategoriaEntity cadastrar(CategoriaEntity categoriaEntity)
    {
        try {
            return repository.save(categoriaEntity);
        }catch (Exception ex){
            throw new CategoriaException("Erro ao cadastrar Categoria", ex);
        }
    }

    @Override
    public CategoriaEntity buscar(Integer id) {
        try {
            return repository.findById(id).orElseThrow(() -> new CategoriaNotFoundException("Não foi Possível encontrar a categoria id: " + id));
        }catch (CategoriaNotFoundException ex){
            throw new CategoriaNotFoundException("Não foi Possível encontrar a categoria id: " + id);
        }catch (Exception ex){
            throw new CategoriaException("Erro ao buscar categoria por id");
        }
    }
}
