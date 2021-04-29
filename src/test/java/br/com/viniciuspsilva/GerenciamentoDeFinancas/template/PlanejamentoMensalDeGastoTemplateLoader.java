package br.com.viniciuspsilva.GerenciamentoDeFinancas.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.PlanejamentoMensalDeGasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enums.MesReferencia;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enums.StatusPlano;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PlanejamentoMensalDeGastoTemplateLoader implements TemplateLoader {
    @Override
    public void load() {
        Fixture.of(PlanejamentoMensalDeGasto.class).addTemplate("OK", new Rule(){
            {
                add("id", 1);
                add("titulo", "janeiro");
                add("descricao", "planejamento de janeiro");
                add("valorPlanejado", new BigDecimal(200));
                add("totalGasto", new BigDecimal(175));
                add("status", StatusPlano.OK);
                add("mesReferencia", MesReferencia.JANEIRO);
                add("dataCriacao", LocalDate.of(2021, 1,1));
                add("idUsuario", 1);
            }
        });
    }
}