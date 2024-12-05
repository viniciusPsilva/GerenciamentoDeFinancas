package br.com.viniciuspsilva.GerenciamentoDeFinancas.gateway.http.controller;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.GastoDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.GastoUpdatedDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.domain.Gasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.GastoEntity;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.mappers.GastoMapper;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.mappers.GastoUpdatedMapper;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.service.GastoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/financas/gasto")
public class GastoController {

    @Autowired
    private GastoService gastoService;

    private final GastoMapper gastoMapper = GastoMapper.INSTANCE;

    private final GastoUpdatedMapper gastoUpdatedMapper = GastoUpdatedMapper.INSTANCE;

    @PostMapping
    public ResponseEntity<GastoDto> cadastrar(@RequestBody @Valid GastoDto gastoDto) {
        Gasto gasto = gastoMapper.mapFromDto(gastoDto);
        Gasto gastoPersistido = gastoService.cadastrarGasto(gasto);
        URI uri = URI.create("financas/gasto/" + gastoPersistido.getId());
        return ResponseEntity.created(uri).body(gastoMapper.mapToDto(gastoPersistido));
    }

    @GetMapping
    public ResponseEntity<List<GastoDto>> listar() {
        List<Gasto> gastos = gastoService.listarGastos();
        return ResponseEntity.ok(gastoMapper.mapToDtoList(gastos));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<GastoDto> buscar(@PathVariable Integer id) {
        Gasto gasto = gastoService.buscar(id);
        return ResponseEntity.ok(gastoMapper.mapToDto(gasto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Integer id) {
        gastoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<GastoUpdatedDto> atualizar(@PathVariable("id") Integer id , @RequestBody @Valid GastoUpdatedDto gastoDto){
        GastoEntity gastoEntity = gastoUpdatedMapper.mapFromGastoUpdatedDto(gastoDto);
        GastoEntity gastoEntityAtualizado = gastoService.atualizar(gastoEntity, id);
        return ResponseEntity.ok().body(gastoUpdatedMapper.mapFromGasto(gastoEntityAtualizado));
    }

}
