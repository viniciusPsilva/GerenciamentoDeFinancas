package br.com.viniciuspsilva.GerenciamentoDeFinancas.service.impl;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.exception.planoDeGasto.PlanoDeGastoNotFoundException;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.gateway.repository.PlanoDeGastoRepository;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.Gasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.PlanoDeGasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.service.PlanoDeGastoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PlanoDeGastoServiceImpl implements PlanoDeGastoService {

    private final PlanoDeGastoRepository repository;

    @Override
    public PlanoDeGasto buscar(Integer id) {
        PlanoDeGasto plano;
        Optional<PlanoDeGasto> planoOptional = repository.findById(id);

        if (planoOptional.isPresent()){
            plano = planoOptional.get();
        }else{
            throw new PlanoDeGastoNotFoundException("Não foi possível encontrar o plano de gasto: "+ id);
        }

        return plano;
    }
}
