package br.com.viniciuspsilva.GerenciamentoDeFinancas.service.impl;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.gateway.repository.UsuarioRepository;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.domain.Usuario;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities.UsuarioEntity;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.mappers.UsuarioMapper;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public Usuario cadastrar(Usuario usuario) {

        UsuarioEntity usuarioEntity = UsuarioMapper.INSTANCE.mapToEntity(usuario);

        UsuarioEntity save = usuarioRepository.save(usuarioEntity);

        return usuario;
    }
}
