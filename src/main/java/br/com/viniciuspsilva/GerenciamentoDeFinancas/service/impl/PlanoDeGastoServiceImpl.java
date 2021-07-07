package br.com.viniciuspsilva.GerenciamentoDeFinancas.service.impl;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.exception.planoDeGasto.PlanoDeGastoNotFoundException;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.gateway.repository.PlanoDeGastoRepository;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.PlanejamentoMensalDeGasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.service.PlanejamentoMensalDeGastoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PlanoDeGastoServiceImpl implements PlanejamentoMensalDeGastoService {

    private final PlanoDeGastoRepository repository;

    @Override
    public PlanejamentoMensalDeGasto buscar(Integer id) {
        return repository.findById(id).orElseThrow(() -> new PlanoDeGastoNotFoundException("Não foi possível encontrar o plano de gasto: " + id));
    }
}
