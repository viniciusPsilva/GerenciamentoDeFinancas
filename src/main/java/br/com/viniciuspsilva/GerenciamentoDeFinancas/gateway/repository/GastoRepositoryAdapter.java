package br.com.viniciuspsilva.GerenciamentoDeFinancas.gateway.repository;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.exception.gasto.GastoNotFoundException;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.domain.Gasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.GastoEntity;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.mappers.GastoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GastoRepositoryAdapter {
    @Autowired
    private GastoRepository repository;

    public Gasto cadastrar(Gasto gasto){
        GastoEntity gastoPersistido = repository.save(GastoMapper.INSTANCE.mapToEntity(gasto));
        return GastoMapper.INSTANCE.mapFromEntity(gastoPersistido);
    }

    public List<Gasto> listarGastos(){
        List<Gasto> gastos = GastoMapper.INSTANCE.mapFromEntityList(repository.findAll());
        return gastos;
    }

    public Gasto buscar(Integer id){
        Optional<GastoEntity> gasto = repository.findById(id);

        if (gasto.isEmpty()) {
            throw new GastoNotFoundException("O gasto com id informado não foi encontrado");
        }

        return GastoMapper.INSTANCE.mapFromEntity(gasto.get());
    }

    public void deletar(Integer id){
        repository.delete(GastoMapper.INSTANCE.mapToEntity(buscar(id)));
    }

}
