package br.com.viniciuspsilva.GerenciamentoDeFinancas.gateway.http.controller;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.gateway.repository.CategoriaRepository;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.CategoriaDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.domain.Categoria;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.CategoriaEntity;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.mappers.CategoriaMapper;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/financas/categoria")
public class CategoriaController {

    @Autowired
    public CategoriaService categoriaService;

    @Autowired
    public CategoriaRepository repository;

    @GetMapping
    public ResponseEntity<?> listarCategoria() {
        Iterable<CategoriaEntity> all = repository.findAll();
        return ResponseEntity.ok(all);
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid CategoriaDto categoriaDto) {
        Categoria categoria = CategoriaMapper.INSTANCE.mapFromCategoriaDto(categoriaDto);
        CategoriaEntity categoriaEntity = CategoriaMapper.INSTANCE.mapToEntity(categoria);
        URI location = URI.create("financas/categoria" + categoriaService.cadastrar(categoriaEntity).getId());
        return ResponseEntity.created(location).build();
    }

}
