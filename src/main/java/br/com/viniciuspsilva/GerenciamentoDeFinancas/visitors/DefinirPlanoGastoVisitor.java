package br.com.viniciuspsilva.GerenciamentoDeFinancas.visitors;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.exception.categoria.CategoriaException;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.exception.usuario.UsuarioException;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.gateway.repository.PlanoDeGastoRepository;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.domain.Categoria;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.domain.Gasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.domain.PlanoDeGasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.domain.Usuario;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.CategoriaEntity;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.PlanoDeGastoEntity;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.mappers.CategoriaMapper;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.mappers.PlanoDeGastoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DefinirPlanoGastoVisitor implements Visitor<Gasto, Gasto>{

    @Autowired
    private PlanoDeGastoRepository planoGastoRepository;

    @Override
    public Gasto visit(Gasto gasto) {
        String tituloPlano = Optional.of(gasto).map(Gasto::getPlanoDeGasto).map(PlanoDeGasto::getTitulo).orElseThrow(() -> new CategoriaException("O Nome da categoria não foi informado."));
        String idUsuario = Optional.of(gasto).map(Gasto::getUsuario).map(Usuario::getId).orElseThrow(() -> new UsuarioException("O Usuario dono do gasto não foi encontrado"));

        PlanoDeGastoEntity planoDeGastoEntity = planoGastoRepository.findByTituloAndIdUsuario(tituloPlano, idUsuario);
        gasto.setPlanoDeGasto(PlanoDeGastoMapper.INSTANCE.mapFromEntity(planoDeGastoEntity));

        return gasto;
    }
}
