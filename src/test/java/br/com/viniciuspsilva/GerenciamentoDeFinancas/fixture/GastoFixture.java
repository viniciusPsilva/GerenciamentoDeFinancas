package br.com.viniciuspsilva.GerenciamentoDeFinancas.fixture;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.CategoriaDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.GastoDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.PlanoDeGastoDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.Categoria;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.Gasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.PlanoDeGasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enumerations.Prioridade;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enumerations.StatusGasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enumerations.Tipo;

import java.math.BigDecimal;
import java.time.LocalDate;

public class GastoFixture {

    public static GastoDto gastoDto(){
        return GastoDto.builder()
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
                .build();
    }

    public static Gasto gasto(){
        return Gasto.builder()
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
                .categoria(Categoria.builder().build())
                .build();
    }


}
