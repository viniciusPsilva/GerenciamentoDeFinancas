package br.com.viniciuspsilva.GerenciamentoDeFinancas.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.domain.Gasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enums.MesReferencia;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enums.Prioridade;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enums.StatusGasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enums.Tipo;

import java.math.BigDecimal;
import java.time.LocalDate;

public class GastoTemplateLoader implements TemplateLoader {
    @Override
    public void load() {
        Fixture.of(Gasto.class).addTemplate("valid", new Rule(){{
            add("id", 1);
            add("nome", "Gasto na Padaria");
            add("descricao", "compra de pao e leite na padaria");
            add("valor", BigDecimal.valueOf(10.00));
            add("mesReferencia", MesReferencia.JANEIRO);
            add("dataVencimento", LocalDate.now().plusDays(1));
            add("tipo", Tipo.GASTO);
            add("status", StatusGasto.PAGO);
            add("prioridade", Prioridade.DESPRIORIZADO);
            add("totalParcelas", 0);
            add("parcelaAtual", 0);
            add("dataCriacao", LocalDate.now());
            add("idPlanoDeGasto", 1);
            add("idCategoria", 1);
        }});

    }
}
