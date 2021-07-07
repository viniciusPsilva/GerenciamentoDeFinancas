package br.com.viniciuspsilva.GerenciamentoDeFinancas.gateway.http.controller;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.GastoDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.GastoUpdatedDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.Gasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.mappers.GastoMapper;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.mappers.GastoUpdatedMapper;
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
    private GastoService gastoService;

    private final GastoMapper gastoMapper = GastoMapper.INSTANCE;

    private final GastoUpdatedMapper gastoUpdatedMapper = GastoUpdatedMapper.INSTANCE;

    @PostMapping
    public ResponseEntity<GastoDto> cadastrar(@RequestBody @Valid GastoDto gastoDto) {
        Gasto gasto = gastoMapper.mapFromGastoDto(gastoDto);
        GastoDto gastoPersistido = gastoMapper.mapFromGasto(gastoService.cadastrarGasto(gasto));
        URI uri = URI.create("financas/gasto/" + gastoPersistido.getId());
        return ResponseEntity.created(uri).body(null);
    }

    @GetMapping
    public ResponseEntity<Iterable<GastoDto>> listar() {
        Iterable<GastoDto> gastos = gastoMapper.mapFromGastoList(gastoService.listarGastos());
        return ResponseEntity.ok(gastos);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<GastoDto> buscar(@PathVariable Integer id) {
        GastoDto gasto = gastoMapper.mapFromGasto(gastoService.buscar(id));
        return ResponseEntity.ok(gasto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Integer id) {
        gastoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<GastoUpdatedDto> atualizar(@PathVariable("id") Integer id , @RequestBody @Valid GastoUpdatedDto gastoDto){
        Gasto gasto = gastoUpdatedMapper.mapFromGastoUpdatedDto(gastoDto);
        Gasto gastoAtualizado = gastoService.atualizar(gasto, id);
        return ResponseEntity.ok().body(gastoUpdatedMapper.mapFromGasto(gastoAtualizado));
    }

}
