package br.com.viniciuspsilva.GerenciamentoDeFinancas.gateway.http.controller;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.CategoriaDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.request.CategoriaFormUpdateDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.domain.Categoria;
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

    @GetMapping
    public ResponseEntity<Iterable<CategoriaDto>> listar() {
        Iterable<Categoria> categorias = categoriaService.listar();
        return ResponseEntity.ok(CategoriaMapper.INSTANCE.mapToDto(categorias));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDto> buscar(@PathVariable Integer id) {
        Categoria categoria = categoriaService.buscar(id);

        return ResponseEntity.ok().body(CategoriaMapper.INSTANCE.mapToDto(categoria));
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid CategoriaDto categoriaDto) {
        Categoria categoria = CategoriaMapper.INSTANCE.mapFromDto(categoriaDto);
        Categoria categoriaPersistida = categoriaService.cadastrar(categoria);
        URI location = URI.create("/financas/categoria/" + categoriaPersistida.getId());
        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CategoriaDto> atualizar(@PathVariable Integer id, @RequestBody @Valid final CategoriaFormUpdateDto updateDto) {
        Categoria categoriaAtualizada = categoriaService.atualizar(id, CategoriaMapper.INSTANCE.mapFromDto(updateDto));
        return ResponseEntity.ok(CategoriaMapper.INSTANCE.mapToDto(categoriaAtualizada));
    }

}
