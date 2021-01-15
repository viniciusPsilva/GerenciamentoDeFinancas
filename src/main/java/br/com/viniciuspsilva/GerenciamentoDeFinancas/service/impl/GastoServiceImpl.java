package br.com.viniciuspsilva.GerenciamentoDeFinancas.service.impl;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.gateway.repository.GastoRepository;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.GastoDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.Gasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.mappers.GastoMapper;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.service.GastoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GastoServiceImpl implements GastoService {

    private final GastoRepository repository;
    private final GastoMapper gastoMapper;

    @Override
    public GastoDto cadastrarGasto(GastoDto gastoDto) {
        Gasto gasto = gastoMapper.toEntity(gastoDto);
        Gasto gastoPersistido = repository.save(gasto);
        return gastoMapper.toDto(gastoPersistido);
    }
}
