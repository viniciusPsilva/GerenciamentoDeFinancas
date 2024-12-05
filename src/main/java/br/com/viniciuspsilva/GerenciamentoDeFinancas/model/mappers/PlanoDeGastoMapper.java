package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.mappers;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.request.PlanoDeGastoRequestDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.request.PlanoDeGastoFormUpdateDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.response.PlanoDeGastoResponseDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.domain.PlanoDeGasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.PlanoDeGastoEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class PlanoDeGastoMapper {

    public static PlanoDeGastoMapper INSTANCE = Mappers.getMapper(PlanoDeGastoMapper.class);

    public abstract Iterable<PlanoDeGasto> mapfromEntity(Iterable<PlanoDeGastoEntity> planos);

    public abstract PlanoDeGasto mapFromEntity(PlanoDeGastoEntity source);

    public abstract PlanoDeGasto mapFromDto(PlanoDeGastoRequestDto source);

    public abstract PlanoDeGasto mapFromDto(PlanoDeGastoFormUpdateDto source);

    public abstract PlanoDeGastoResponseDto mapToDto(PlanoDeGasto source);

    public abstract Iterable<PlanoDeGastoResponseDto> mapToDto(Iterable<PlanoDeGasto> source);

    public abstract PlanoDeGastoEntity mapToEntity(PlanoDeGasto source);

}
