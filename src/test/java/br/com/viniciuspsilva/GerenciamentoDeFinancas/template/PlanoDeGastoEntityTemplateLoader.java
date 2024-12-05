package br.com.viniciuspsilva.GerenciamentoDeFinancas.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.PlanoDeGastoEntity;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enums.StatusPlano;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PlanoDeGastoEntityTemplateLoader implements TemplateLoader {
    @Override
    public void load() {
        Fixture.of(PlanoDeGastoEntity.class).addTemplate("OK", new Rule(){
            {
                add("id", 1);
                add("titulo", "janeiro");
                add("descricao", "planejamento de janeiro");
                add("valorPlanejado", new BigDecimal(200));
                add("totalGasto", new BigDecimal(175));
                add("status", StatusPlano.OK);
                add("dataCriacao", LocalDate.of(2021, 1,1));
                add("idUsuario", "cb98326b-d84b-4f98-a2ad-388a87736ae7");
            }
        });

        Fixture.of(PlanoDeGastoEntity.class).addTemplate("OK_id_2", new Rule(){
            {
                add("id", 2);
                add("titulo", "Fevereiro");
                add("descricao", "planejamento de fevereiro");
                add("valorPlanejado", new BigDecimal(350));
                add("totalGasto", new BigDecimal(175));
                add("status", StatusPlano.OK);
                add("dataCriacao", LocalDate.of(2021, 2,1));
                add("idUsuario", "cb98326b-d84b-4f98-a2ad-388a87736ae7");
            }
        });
    }
}