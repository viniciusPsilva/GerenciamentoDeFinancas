package br.com.viniciuspsilva.GerenciamentoDeFinancas.gateway.http.controller;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.gateway.repository.PlanoDeGastoRepository;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.PlanejamentoMensalDeGastoDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.domain.PlanejamentoMensalDeGasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.PlanejamentoMensalDeGastoEntity;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.mappers.PlanejamentoMensalDeGastoMapper;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.service.PlanejamentoMensalDeGastoService;
import lombok.RequiredArgsConstructor;
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
    private PlanejamentoMensalDeGastoService planejamentoMensalDeGastoService;

    @GetMapping
    public ResponseEntity listar(){
        Iterable<PlanejamentoMensalDeGastoEntity> planos = repository.findAll();
        return ResponseEntity.ok(planos);
    }

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid PlanejamentoMensalDeGastoDto planoDto){
        final PlanejamentoMensalDeGasto planejamentoMensalDeGasto = PlanejamentoMensalDeGastoMapper.INSTANCE.mapFromPladoDeGastoDto(planoDto);

        PlanejamentoMensalDeGasto planejamentoCadastrado = planejamentoMensalDeGastoService.cadastrar(planejamentoMensalDeGasto);

        return ResponseEntity.created(URI.create("/financas/plano/"+ planejamentoCadastrado.getId())).build();
    }

}
