package br.com.viniciuspsilva.GerenciamentoDeFinancas.fixture;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.GastoDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.Gasto;

public class GastoFixture {

    public static GastoDto gastoDto(){
        return null;
        /*return GastoDto.builder()
                .nome("Gasto Teste")
                .descricao("Gasto Teste")
                .valor(BigDecimal.valueOf(200.00))
                .dataReferencia(LocalDate.of(2021, 02,01 ))
                .dataVencimento(LocalDate.now().plusMonths(1))
                .tipo(Tipo.GASTO)
                .status(StatusGasto.EM_ABERTO)
                .prioridade(Prioridade.BAIXA)
                .dataCriacao(LocalDate.now())
                .idPlanoDeGasto(1)
                .idCategoria(1)
                .build();*/
    }

    public static Gasto gasto(){
        return null;
        /*return Gasto.builder()
                .nome("Gasto Teste")
                .descricao("Gasto Teste")
                .valor(BigDecimal.valueOf(200.00))
                .dataReferencia(LocalDate.of(2021, 02,01 ))
                .dataVencimento(LocalDate.now().plusMonths(1))
                .tipo(Tipo.GASTO)
                .status(StatusGasto.EM_ABERTO)
                .prioridade(Prioridade.BAIXA)
                .dataCriacao(LocalDate.now())
                .planoDeGasto(PlanoDeGasto.builder().build())
                //.categoria(Categoria.builder().build())
                .build();*/
    }


}
