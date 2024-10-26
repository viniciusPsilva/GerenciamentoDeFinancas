package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.mappers;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.PlanoDeGastoDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.PlanoDeGastoFormUpdateDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.domain.PlanoDeGasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.PlanoDeGastoEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class PlanoDeGastoMapper {

    public static PlanoDeGastoMapper INSTANCE = Mappers.getMapper(PlanoDeGastoMapper.class);

    public abstract Iterable<PlanoDeGasto> mapfromEntity(Iterable<PlanoDeGastoEntity> planos);
    public abstract PlanoDeGasto mapFromEntity(PlanoDeGastoEntity source);
    public abstract PlanoDeGasto mapFromDto(PlanoDeGastoDto source);
    public abstract PlanoDeGasto mapFromDto(PlanoDeGastoFormUpdateDto source);
    public abstract PlanoDeGastoDto mapToDto(PlanoDeGasto source);
    public abstract PlanoDeGastoEntity mapToEntity(PlanoDeGasto source);

}
