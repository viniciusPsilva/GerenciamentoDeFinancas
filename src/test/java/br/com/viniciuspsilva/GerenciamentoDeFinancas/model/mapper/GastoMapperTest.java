package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.mapper;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.GastoEntity;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.mappers.GastoMapper;
import org.junit.jupiter.api.Test;

public class GastoMapperTest {

    @Test
    public void deveMapearGastoParaGastoDto(){
        //TOOD continuar teste
        GastoEntity gastoEntity = new GastoEntity();
        gastoEntity.setId(1);
        GastoMapper.INSTANCE.mapFromGasto(gastoEntity);
    }

}
