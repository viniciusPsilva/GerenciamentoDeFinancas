package br.com.viniciuspsilva.GerenciamentoDeFinancas.gateway.http.controller;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.GastoDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.Gasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.service.GastoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/financas/gasto")
@RequiredArgsConstructor
public class GastoController {

    private final GastoService gastoService;

    @PostMapping
    public ResponseEntity<GastoDto> cadastrar(@RequestBody @Valid  GastoDto gastoDto){
        GastoDto gastoPersistido = gastoService.cadastrarGasto(gastoDto);
        URI uri = URI.create( "financas/gasto/" + gastoPersistido.getId());
        return ResponseEntity.created(uri).body(null);
    }

    @GetMapping
    public ResponseEntity<Iterable<GastoDto>> listar(){
        Iterable<GastoDto> gastoDtos = gastoService.listarGastos();
        return ResponseEntity.ok(gastoDtos);
    }

}
