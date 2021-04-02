package br.com.viniciuspsilva.GerenciamentoDeFinancas.service.impl;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.exception.gasto.GastoNotFoundException;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.gateway.repository.GastoRepository;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.GastoDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.Gasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.mappers.GastoMapper;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.service.GastoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GastoServiceImpl implements GastoService {

    private final GastoRepository repository;
    private final GastoMapper gastoMapper = GastoMapper.INSTANCE;

    @Override
    public GastoDto cadastrarGasto(final GastoDto gastoDto) {
        final Gasto gasto = gastoMapper.mapFromGastoDto(gastoDto);
        final Gasto gastoPersistido = repository.save(gasto);
        return gastoMapper.mapFromGasto(gastoPersistido);
    }

    @Override
    public Iterable<GastoDto> listarGastos() {
        Iterable<Gasto> allGastos = repository.findAll();
        return gastoMapper.mapFromGastoList(allGastos);
    }

    @Override
    public GastoDto buscar(Integer id) {
        Optional<Gasto> gasto = repository.findById(id);

        if (gasto.isEmpty()){
            throw new GastoNotFoundException("O gasto com id informado não foi encontrado");
        }

        return gastoMapper.mapFromGasto(gasto.get());
    }


}
