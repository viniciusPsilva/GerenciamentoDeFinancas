package br.com.viniciuspsilva.GerenciamentoDeFinancas.service.impl;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.exception.planoDeGasto.PlanoDeGastoException;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.exception.planoDeGasto.PlanoDeGastoNotFoundException;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.gateway.repository.PlanoDeGastoRepository;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.domain.PlanoDeGasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.PlanoDeGastoEntity;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.mappers.PlanoDeGastoMapper;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.service.PlanoDeGastoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

@Service
public class PlanoDeGastoServiceImpl implements PlanoDeGastoService {
    @Autowired
    private PlanoDeGastoRepository repository;

    @Override
    public PlanoDeGasto buscar(Integer id) {
        PlanoDeGastoEntity planoDeGastoEntity = repository.findById(id).orElseThrow(() -> new PlanoDeGastoNotFoundException("Não foi possível encontrar o plano de gasto: " + id));
        return PlanoDeGastoMapper.INSTANCE.mapFromEntity(planoDeGastoEntity);
    }

    @Override
    public PlanoDeGasto cadastrar(PlanoDeGasto plano) {
        final PlanoDeGastoEntity planejamentoMensalDeGastoEntity = PlanoDeGastoMapper.INSTANCE.mapToEntity(plano);
        final PlanoDeGastoEntity planejamentoPersistido;

        try {
            planejamentoPersistido = repository.save(planejamentoMensalDeGastoEntity);
        }catch (DataIntegrityViolationException ex){
            throw new PlanoDeGastoException("Erro ao tentar cadastrar um plano de gasto, verifique se o plano já existe.");
        }catch (Exception ex){
            throw new PlanoDeGastoException("Erro ao tentar cadastrar um plano de gasto.");
        }

        return PlanoDeGastoMapper.INSTANCE.mapFromEntity(planejamentoPersistido);
    }

    @Override
    public Iterable<PlanoDeGasto> listar() {
        Iterable<PlanoDeGastoEntity> planos = repository.findAll();
        return PlanoDeGastoMapper.INSTANCE.mapfromEntity(planos);
    }

    @Override
    public PlanoDeGasto atualizar(String id, PlanoDeGasto updated) {

        final PlanoDeGasto planoDeGasto = buscar(Integer.valueOf(id));

        atualizarDadosPlano(planoDeGasto, updated);

        PlanoDeGastoEntity planoAtualizadoPersistido = repository.save(PlanoDeGastoMapper.INSTANCE.mapToEntity(planoDeGasto));

        return PlanoDeGastoMapper.INSTANCE.mapFromEntity(planoAtualizadoPersistido);
    }

    private void atualizarDadosPlano(final PlanoDeGasto planoDeGasto, final PlanoDeGasto updated){

        String descricao = updated.getDescricao();
        if (Objects.nonNull(descricao)){
            planoDeGasto.setDescricao(descricao);
        }

        BigDecimal valorPlanejado = updated.getValorPlanejado();
        if (Objects.nonNull(valorPlanejado)){
            planoDeGasto.setValorPlanejado(valorPlanejado);
        }
    }

}
