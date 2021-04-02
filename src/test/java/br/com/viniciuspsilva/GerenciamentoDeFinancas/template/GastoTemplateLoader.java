package br.com.viniciuspsilva.GerenciamentoDeFinancas.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.Gasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enumerations.Prioridade;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enumerations.StatusGasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enumerations.Tipo;

import java.math.BigDecimal;
import java.time.LocalDate;

public class GastoTemplateLoader implements TemplateLoader {
    @Override
    public void load() {
        Fixture.of(Gasto.class).addTemplate("gasto", new Rule(){{
            add("id", 1);
            add("nome", "Gasto na Padaria");
            add("descricao", "compra de pao e leite na padaria");
            add("valor", BigDecimal.valueOf(10.00));
            add("dataReferencia", LocalDate.of(2021, 1, 1));
            add("dataVencimento", LocalDate.now().plusDays(1));
            add("tipo", Tipo.GASTO);
            add("status", StatusGasto.PAGO);
            add("prioridade", Prioridade.DESPRIORIZADO);
            add("totalParcelas", 0);
            add("parcelaAtual", 0);
            add("dataCriacao", LocalDate.now());
            //add("idPlanoDeGasto", 1);
            //add("idCategoria", 1);
        }});
    }
}
