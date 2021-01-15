package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.mappers;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.PlanoDeGastoDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.PlanoDeGasto;

public class PlanoDeGastoMapper {
    public static PlanoDeGastoDto toDto(PlanoDeGasto planoDeGasto){
        return PlanoDeGastoDto.builder()
                .titulo(planoDeGasto.getTitulo())
                .descricao(planoDeGasto.getDescricao())
                .valorPlanejado(planoDeGasto.getValorPlanejado())
                .totalGasto(planoDeGasto.getTotalGasto())
                .status(planoDeGasto.getStatus())
                .dataReferencia(planoDeGasto.getDataReferencia())
                .dataCriacao(planoDeGasto.getDataCriacao())
                .build();
    }

    public static PlanoDeGasto toEntity(PlanoDeGastoDto planoDeGastoDto){
        return PlanoDeGasto.builder()
                .titulo(planoDeGastoDto.getTitulo())
                .descricao(planoDeGastoDto.getDescricao())
                .valorPlanejado(planoDeGastoDto.getValorPlanejado())
                .totalGasto(planoDeGastoDto.getTotalGasto())
                .status(planoDeGastoDto.getStatus())
                .dataReferencia(planoDeGastoDto.getDataReferencia())
                .build();
    }
}
