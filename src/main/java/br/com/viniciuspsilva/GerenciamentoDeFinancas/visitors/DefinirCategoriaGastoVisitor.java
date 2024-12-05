package br.com.viniciuspsilva.GerenciamentoDeFinancas.visitors;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.exception.categoria.CategoriaException;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.exception.categoria.CategoriaNotFoundException;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.exception.usuario.UsuarioException;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.gateway.repository.CategoriaRepository;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.gateway.repository.GastoRepository;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.domain.Categoria;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.domain.Gasto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.domain.Usuario;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.CategoriaEntity;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.mappers.CategoriaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DefinirCategoriaGastoVisitor implements Visitor<Gasto, Gasto>{

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public Gasto visit(Gasto gasto) {
        String nomeCategoria = Optional.of(gasto).map(Gasto::getCategoria).map(Categoria::getNome).orElseThrow(() -> new CategoriaException("O Nome da categoria não foi informado."));
        String idUsuario = Optional.of(gasto).map(Gasto::getUsuario).map(Usuario::getId).orElseThrow(() -> new UsuarioException("O Usuario dono do gasto não foi encontrado"));

        CategoriaEntity categoriaEntity = categoriaRepository.findByNomeAndIdUsuario(nomeCategoria, idUsuario);
        gasto.setCategoria(CategoriaMapper.INSTANCE.mapFromEntity(categoriaEntity));

        return gasto;
    }
}
