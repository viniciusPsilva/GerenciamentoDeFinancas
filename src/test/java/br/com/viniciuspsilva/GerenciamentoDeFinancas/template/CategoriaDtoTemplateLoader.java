package br.com.viniciuspsilva.GerenciamentoDeFinancas.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.CategoriaDto;

public class CategoriaDtoTemplateLoader implements TemplateLoader {
    @Override
    public void load() {
        Fixture.of(CategoriaDto.class).addTemplate("valid", new Rule() {{
            add("nome", "Categoria teste");
            add("descricao", "descrição teste");
        }});
    }
}
