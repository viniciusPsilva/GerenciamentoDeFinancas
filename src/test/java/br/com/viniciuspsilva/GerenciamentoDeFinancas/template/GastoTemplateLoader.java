package br.com.viniciuspsilva.GerenciamentoDeFinancas.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.Categoria;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.Gasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.PlanejamentoMensalDeGasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enums.MesReferencia;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enums.Prioridade;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enums.StatusGasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enums.Tipo;

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
            add("mesReferencia", MesReferencia.JANEIRO);
            add("dataVencimento", LocalDate.now().plusDays(1));
            add("tipo", Tipo.GASTO);
            add("status", StatusGasto.PAGO);
            add("prioridade", Prioridade.DESPRIORIZADO);
            add("totalParcelas", 0);
            add("parcelaAtual", 0);
            add("dataCriacao", LocalDate.now());
            add("planoDeGasto", one(PlanejamentoMensalDeGasto.class, "OK"));
            add("categoria", one(Categoria.class, "valid"));
        }});

        Fixture.of(Gasto.class).addTemplate("target", new Rule(){{
            add("id", 1);
            add("nome", "Gasto");
            add("descricao", "compra");
            add("valor", BigDecimal.valueOf(2.00));
            add("mesReferencia", MesReferencia.FEVEREIRO);
            add("dataVencimento", LocalDate.now().plusDays(3));
            add("tipo", Tipo.PARCELADO);
            add("status", StatusGasto.EM_ABERTO);
            add("prioridade", Prioridade.DESPRIORIZADO);
            add("totalParcelas", 12);
            add("parcelaAtual", 2);
            add("dataCriacao", LocalDate.now());
            add("planoDeGasto", one(PlanejamentoMensalDeGasto.class, "OK"));
            add("categoria", one(Categoria.class, "valid"));
        }});
    }
}
