package br.com.viniciuspsilva.GerenciamentoDeFinancas.service.impl;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.exception.categoria.CategoriaException;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.exception.categoria.CategoriaNotFoundException;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.gateway.repository.CategoriaRepository;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.request.CategoriaFormUpdateDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.domain.Categoria;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.CategoriaEntity;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.mappers.CategoriaMapper;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    @Override
    public Categoria cadastrar(Categoria categoria) {
        try {
            CategoriaEntity categoriaExistente = repository.findByNomeAndIdUsuario(categoria.getNome(), categoria.getIdUsuario());

            if (Objects.nonNull(categoriaExistente))
                throw new CategoriaException("A categoria com o nome informado já existe.");

            CategoriaEntity categoriaEntity = repository.save(CategoriaMapper.INSTANCE.mapToEntity(categoria));
            return CategoriaMapper.INSTANCE.mapFromEntity(categoriaEntity);
        }catch (CategoriaException ex){
            throw ex;
        }
        catch (Exception ex) {
            throw new CategoriaException("Erro ao cadastrar Categoria", ex);
        }
    }

    @Override
    public Categoria buscar(Integer id) {
        try {
            CategoriaEntity categoriaEntity = repository.findById(id).orElseThrow(() -> new CategoriaNotFoundException("Não foi Possível encontrar a categoria id: " + id));
            return CategoriaMapper.INSTANCE.mapFromEntity(categoriaEntity);
        } catch (CategoriaNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new CategoriaException("Erro ao buscar categoria por id");
        }
    }

    @Override
    public Iterable<Categoria> listar() {
        Iterable<CategoriaEntity> categorias = repository.findAll();
        return CategoriaMapper.INSTANCE.mapFromEntity(categorias);
    }

    @Override
    public Categoria atualizar(Integer id, final Categoria categoriaAtualizada) {

        Categoria categoria = buscar(id);
        atualizarDadosCategoria(categoria, categoriaAtualizada);

        CategoriaEntity categoriaAtualizadaPersistida = repository.save(CategoriaMapper.INSTANCE.mapToEntity(categoria));

        return CategoriaMapper.INSTANCE.mapFromEntity(categoriaAtualizadaPersistida);
    }

    private void atualizarDadosCategoria(final Categoria categoriaCadastrada, final  Categoria categoriaAtualizada) {
        String descricao = categoriaAtualizada.getDescricao();
        if (Objects.nonNull(descricao)){
            categoriaCadastrada.setDescricao(descricao);
        }
    }
}
