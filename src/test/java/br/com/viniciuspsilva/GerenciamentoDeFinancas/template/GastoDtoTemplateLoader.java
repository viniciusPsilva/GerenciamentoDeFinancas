package br.com.viniciuspsilva.GerenciamentoDeFinancas.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.GastoDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enums.MesReferencia;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enums.Prioridade;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enums.StatusGasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enums.Tipo;

import java.math.BigDecimal;
import java.time.LocalDate;

public class GastoDtoTemplateLoader implements TemplateLoader {
    @Override
    public void load() {
        Fixture.of(GastoDto.class).addTemplate("gasto", new Rule() {{
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

        Fixture.of(GastoDto.class).addTemplate("frequente", new Rule() {{
            add("id", 1);
            add("nome", "Gasto na Padaria");
            add("descricao", "compra de pão e leite na padaria");
            add("valor", BigDecimal.valueOf(10.00));
            add("mesReferencia", MesReferencia.JANEIRO);
            add("dataVencimento", LocalDate.now().plusDays(1));
            add("tipo", Tipo.FREQUENTE);
            add("status", StatusGasto.EM_ABERTO);
            add("prioridade", Prioridade.ALTA);
            add("totalParcelas", 12);
            add("parcelaAtual", 2);
            add("dataCriacao", LocalDate.now());
            add("idPlanoDeGasto", 1);
            add("idCategoria", 1);
        }});

        Fixture.of(GastoDto.class).addTemplate("parcelado", new Rule() {{
            add("id", 1);
            add("nome", "Gasto na Padaria");
            add("descricao", "compra de pão e leite na padaria");
            add("valor", BigDecimal.valueOf(10.00));
            add("mesReferencia", MesReferencia.JANEIRO);
            add("dataVencimento", LocalDate.now().plusDays(1));
            add("tipo", Tipo.PARCELADO);
            add("status", StatusGasto.EM_ABERTO);
            add("prioridade", Prioridade.ALTA);
            add("totalParcelas", 5);
            add("parcelaAtual", 4);
            add("dataCriacao", LocalDate.now());
            add("idPlanoDeGasto", 1);
            add("idCategoria", 1);
        }});
    }
}
