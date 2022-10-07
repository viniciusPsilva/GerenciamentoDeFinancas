package br.com.viniciuspsilva.GerenciamentoDeFinancas.gateway.http.controller;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.gateway.repository.PlanoDeGastoRepository;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.PlanejamentoMensalDeGastoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/financas/plano")
@RequiredArgsConstructor
public class PlanoDeGastoController {

    private final PlanoDeGastoRepository repository;

    @GetMapping
    public ResponseEntity listar(){
        Iterable<PlanejamentoMensalDeGastoEntity> planos = repository.findAll();
        return ResponseEntity.ok(planos);
    }



}
