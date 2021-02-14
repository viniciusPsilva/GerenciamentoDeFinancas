package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.mapper;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.Gasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.mappers.GastoMapper;
import org.junit.jupiter.api.Test;

public class GastoMapperTest {

    @Test
    public void deveMapearGastoParaGastoDto(){
        //TOOD continuar teste
        Gasto gasto = new Gasto();
        gasto.setId(1);
        GastoMapper.INSTANCE.mapFromGasto(gasto);
    }

}
