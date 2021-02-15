package br.com.viniciuspsilva.GerenciamentoDeFinancas.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.Gasto;

public class GastoTemplateLoader implements TemplateLoader {
    @Override
    public void load() {
        Fixture.of(Gasto.class).addTemplate("valid", new Rule(){{

        }});
    }
}
