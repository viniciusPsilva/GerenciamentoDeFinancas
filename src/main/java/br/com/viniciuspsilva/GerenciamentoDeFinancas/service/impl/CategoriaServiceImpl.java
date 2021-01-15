package br.com.viniciuspsilva.GerenciamentoDeFinancas.service.impl;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.exception.categoria.CategoriaNotFoundException;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.gateway.repository.CategoriaRepository;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.CategoriaDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.Categoria;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository repository;

    @Override
    public CategoriaDto cadastrar(CategoriaDto categoriaDto) {
        return null;
    }

    @Override
    public Categoria buscar(Integer id) {

        Optional<Categoria> categoriaOptional = Optional.ofNullable(repository.findById(id))
                .orElseThrow(() -> new CategoriaNotFoundException("Não foi Possível encontrar a categoria id: " + id));

        return categoriaOptional.get();
    }
}
