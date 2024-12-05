package br.com.viniciuspsilva.GerenciamentoDeFinancas.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.CategoriaEntity;

import java.time.LocalDate;

public class CategoriaEntityTemplateLoader implements TemplateLoader {
    @Override
    public void load() {
        Fixture.of(CategoriaEntity.class).addTemplate("valid", new Rule() {
            {
                add("id", 1);
                add("nome", "Lazer");
                add("descricao", "gastos com Lazer");
                add("dataCriacao", LocalDate.of(2021, 1, 1));
            }
        });

        Fixture.of(CategoriaEntity.class).addTemplate("valid_id_2", new Rule() {
            {
                add("id", 2);
                add("nome", "Educação");
                add("descricao", "gastos com Educação");
                add("dataCriacao", LocalDate.of(2021, 1, 1));
            }
        });
    }
}