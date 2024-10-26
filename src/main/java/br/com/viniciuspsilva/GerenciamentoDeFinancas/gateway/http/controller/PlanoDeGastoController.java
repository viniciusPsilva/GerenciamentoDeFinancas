package br.com.viniciuspsilva.GerenciamentoDeFinancas.gateway.http.controller;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.gateway.repository.PlanoDeGastoRepository;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.PlanejamentoMensalDeGastoDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.domain.PlanoDeGasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.mappers.PlanoDeGastoMapper;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.validator.PlanejamentoMensalDeGastoValidator;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.service.PlanoDeGastoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/financas/plano")
public class PlanoDeGastoController {
    @Autowired
    private PlanoDeGastoRepository repository;

    @Autowired
    private PlanejamentoMensalDeGastoValidator validator;

    @Autowired
    private PlanoDeGastoService planoDeGastoService;

    @GetMapping
    public ResponseEntity listar(){
        Iterable<PlanoDeGasto> planos = planoDeGastoService.listar();
        return ResponseEntity.ok(planos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanejamentoMensalDeGastoDto> buscar(@PathVariable Integer id){

        PlanoDeGasto planejamentoDeGasto = planoDeGastoService.buscar(id);

        return ResponseEntity.ok(PlanoDeGastoMapper.INSTANCE.mapToDto(planejamentoDeGasto));
    }

    @PostMapping
    public ResponseEntity<PlanejamentoMensalDeGastoDto> cadastrar(@RequestBody @Valid PlanejamentoMensalDeGastoDto planoDto){

        validator.validate(planoDto);

        final PlanoDeGasto planejamentoMensalDeGasto = PlanoDeGastoMapper.INSTANCE.mapFromDto(planoDto);

        PlanoDeGasto planejamentoCadastrado = planoDeGastoService.cadastrar(planejamentoMensalDeGasto);

        return ResponseEntity.created(URI.create("/financas/plano/"+ planejamentoCadastrado.getId())).build();
    }

}
