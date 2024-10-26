package br.com.viniciuspsilva.GerenciamentoDeFinancas.visitors;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.gateway.repository.GastoRepository;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.domain.Gasto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefinirCategoriaGastoVisitor implements Visitor<Gasto, Gasto>{

    @Autowired
    private GastoRepository repository;

    @Override
    public Gasto visit(Gasto gasto) {

        return null;
    }
}
