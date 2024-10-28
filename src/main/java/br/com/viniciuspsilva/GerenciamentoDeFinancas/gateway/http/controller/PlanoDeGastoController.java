package br.com.viniciuspsilva.GerenciamentoDeFinancas.gateway.http.controller;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.gateway.repository.PlanoDeGastoRepository;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.request.PlanoDeGastoFormUpdateDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.request.PlanoDeGastoRequestDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.response.PlanoDeGastoResponseDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.domain.PlanoDeGasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.mappers.PlanoDeGastoMapper;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.validator.PlanoDeGastoValidator;
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
    private PlanoDeGastoValidator validator;

    @Autowired
    private PlanoDeGastoService planoDeGastoService;

    @GetMapping
    public ResponseEntity<Iterable<PlanoDeGastoResponseDto>> listar(){
        Iterable<PlanoDeGasto> planos = planoDeGastoService.listar();
        return ResponseEntity.ok(PlanoDeGastoMapper.INSTANCE.mapToDto(planos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanoDeGastoResponseDto> buscar(@PathVariable Integer id){

        PlanoDeGasto planejamentoDeGasto = planoDeGastoService.buscar(id);

        return ResponseEntity.ok(PlanoDeGastoMapper.INSTANCE.mapToDto(planejamentoDeGasto));
    }

    @PostMapping
    public ResponseEntity<Void> cadastrar(@RequestBody @Valid PlanoDeGastoRequestDto planoDto){

        validator.validate(planoDto);

        final PlanoDeGasto planejamentoMensalDeGasto = PlanoDeGastoMapper.INSTANCE.mapFromDto(planoDto);

        PlanoDeGasto planejamentoCadastrado = planoDeGastoService.cadastrar(planejamentoMensalDeGasto);

        return ResponseEntity.created(URI.create("/financas/plano/"+ planejamentoCadastrado.getId())).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PlanoDeGastoResponseDto> atualizar(@RequestBody @Valid PlanoDeGastoFormUpdateDto formUpdateDto, @PathVariable final String id){

        PlanoDeGasto planoDeGastoAtualizado = planoDeGastoService.atualizar(id, PlanoDeGastoMapper.INSTANCE.mapFromDto(formUpdateDto));

        return ResponseEntity.ok(PlanoDeGastoMapper.INSTANCE.mapToDto(planoDeGastoAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable final Integer id){

        planoDeGastoService.deletar(id);

        return ResponseEntity.noContent().build();
    }

}
