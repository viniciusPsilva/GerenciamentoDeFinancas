package br.com.viniciuspsilva.GerenciamentoDeFinancas.visitors;

public interface Visitor<Elemento, Retorno> {
    Retorno visit(final Elemento element);
}
