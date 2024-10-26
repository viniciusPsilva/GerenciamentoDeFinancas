package br.com.viniciuspsilva.GerenciamentoDeFinancas.visitors;

public interface Visitable<Element> {
    void accept(final Visitor<Element, ?> visitor);
}
