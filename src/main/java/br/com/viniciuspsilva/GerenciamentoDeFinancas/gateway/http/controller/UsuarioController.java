package br.com.viniciuspsilva.GerenciamentoDeFinancas.gateway.http.controller;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.dataContract.UsuarioDto;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.domain.Usuario;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.mappers.UsuarioMapper;
import br.com.viniciuspsilva.GerenciamentoDeFinancas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/financas/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @PostMapping
    public ResponseEntity<UsuarioDto> cadastrarUsuario(@RequestBody UsuarioDto usuarioDto){

        Usuario usuario = UsuarioMapper.INSTANCE.mapFromDto(usuarioDto);

        Usuario usuarioCadastrado = usuarioService.cadastrar(usuario);

        URI uri = URI.create("financas/usuario/" + usuarioCadastrado.getId());

        return ResponseEntity.created(uri).body(UsuarioMapper.INSTANCE.mapToDto(usuarioCadastrado));
    }

}
