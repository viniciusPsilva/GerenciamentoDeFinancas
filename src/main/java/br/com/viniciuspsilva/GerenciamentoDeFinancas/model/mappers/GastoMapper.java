package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.mappers;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.GastoDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.Categoria;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.Gasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.service.CategoriaService;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.service.PlanoDeGastoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@RequiredArgsConstructor
@Component
public class GastoMapper {

    private final CategoriaService categoriaService;
    private final PlanoDeGastoService planoDeGastoService;

    public GastoDto toDto(Gasto gasto) {
        return GastoDto.builder()
                .id(gasto.getId())
                .nome(gasto.getNome())
                .descricao(gasto.getDescricao())
                .valor(gasto.getValor())
                .dataReferencia(gasto.getDataReferencia())
                .dataVencimento(gasto.getDataVencimento())
                .tipo(gasto.getTipo())
                .status(gasto.getStatus())
                .prioridade(gasto.getPrioridade())
                .totalParcelas(gasto.getTotalParcelas())
                .parcelaAtual(gasto.getParcelaAtual())
                .dataCriacao(gasto.getDataCriacao())
                .idPlanoDeGasto(gasto.getPlanoDeGasto().getId())
                .idCategoria(gasto.getCategoria().getId())
                .build();
    }

    public Gasto toEntity(GastoDto gastoDto) {
        return Gasto.builder()
                .nome(gastoDto.getNome())
                .descricao(gastoDto.getDescricao())
                .valor(gastoDto.getValor())
                .dataReferencia(gastoDto.getDataReferencia())
                .dataVencimento(gastoDto.getDataVencimento())
                .dataCriacao(LocalDate.now())
                .tipo(gastoDto.getTipo())
                .status(gastoDto.getStatus())
                .prioridade(gastoDto.getPrioridade())
                .totalParcelas(gastoDto.getTotalParcelas())
                .parcelaAtual(gastoDto.getParcelaAtual())
                .planoDeGasto(planoDeGastoService.buscar(gastoDto.getIdPlanoDeGasto()))
                .categoria(categoriaService.buscar(gastoDto.getIdCategoria()))
                .build();
    }


}
