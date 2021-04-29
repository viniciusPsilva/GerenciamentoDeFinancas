package br.com.viniciuspsilva.GerenciamentoDeFinancas.gateway.http.controller;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.GastoDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.Gasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.mappers.GastoMapper;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.service.GastoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/financas/gasto")
public class GastoController {

    @Autowired
    private  GastoService gastoService;

    private final GastoMapper gastoMapper = GastoMapper.INSTANCE;

    @PostMapping
    public ResponseEntity<GastoDto> cadastrar(@RequestBody @Valid  GastoDto gastoDto){
        Gasto gasto = gastoMapper.mapFromGastoDto(gastoDto);
        GastoDto gastoPersistido = gastoMapper.mapFromGasto(gastoService.cadastrarGasto(gasto));
        URI uri = URI.create( "financas/gasto/" + gastoPersistido.getId());
        return ResponseEntity.created(uri).body(null);
    }

    @GetMapping
    public ResponseEntity<Iterable<GastoDto>> listar(){
        Iterable<GastoDto> gastos = gastoMapper.mapFromGastoList(gastoService.listarGastos());
        return ResponseEntity.ok(gastos);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<GastoDto> buscar(@PathVariable Integer id){
        GastoDto gasto = gastoMapper.mapFromGasto(gastoService.buscar(id));
        return ResponseEntity.ok(gasto);
    }
}
