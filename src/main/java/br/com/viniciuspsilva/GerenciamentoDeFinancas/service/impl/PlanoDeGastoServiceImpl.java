package br.com.viniciuspsilva.GerenciamentoDeFinancas.service.impl;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.exception.planoDeGasto.PlanoDeGastoException;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.exception.planoDeGasto.PlanoDeGastoNotFoundException;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.gateway.repository.PlanoDeGastoRepository;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.domain.PlanejamentoMensalDeGasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.PlanejamentoMensalDeGastoEntity;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.mappers.PlanejamentoMensalDeGastoMapper;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.service.PlanejamentoMensalDeGastoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlanoDeGastoServiceImpl implements PlanejamentoMensalDeGastoService {
    @Autowired
    private PlanoDeGastoRepository repository;

    @Override
    public PlanejamentoMensalDeGastoEntity buscar(Integer id) {
        return repository.findById(id).orElseThrow(() -> new PlanoDeGastoNotFoundException("Não foi possível encontrar o plano de gasto: " + id));
    }

    @Override
    public PlanejamentoMensalDeGasto cadastrar(PlanejamentoMensalDeGasto plano) {
        final PlanejamentoMensalDeGastoEntity planejamentoMensalDeGastoEntity = PlanejamentoMensalDeGastoMapper.INSTANCE.mapToEntity(plano);
        final PlanejamentoMensalDeGastoEntity planejamentoSalvo;

        try {
            planejamentoSalvo = repository.save(planejamentoMensalDeGastoEntity);
        }catch (DataIntegrityViolationException ex){
            throw new PlanoDeGastoException("Erro ao tentar cadastrar um plano de gasto, verifique se o plano já existe.");
        }catch (Exception ex){
            throw new PlanoDeGastoException("Erro ao tentar cadastrar um plano de gasto.");
        }

        return PlanejamentoMensalDeGastoMapper.INSTANCE.mapFromPlanoDeGastoEntity(planejamentoSalvo);
    }

}
