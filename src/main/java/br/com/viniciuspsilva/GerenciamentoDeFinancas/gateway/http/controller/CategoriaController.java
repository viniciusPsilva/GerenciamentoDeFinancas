package br.com.viniciuspsilva.GerenciamentoDeFinancas.gateway.http.controller;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.gateway.repository.CategoriaRepository;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.Categoria;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/financas/categoria")
@RequiredArgsConstructor
public class CategoriaController {

    public final CategoriaService categoriaService;

    public final CategoriaRepository repository;

    @GetMapping
    public ResponseEntity listarCategoria(){
        Iterable<Categoria> all = repository.findAll();
        return ResponseEntity.ok(all);
    }

}
