package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities;

import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity(name = "usuario")
@Setter
public class UsuarioEntity {

    @Id
    @Column(name = "id_usuario", updatable = false)
    private String id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String sobreNome;

    @Column(nullable = true)
    private String apelido;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false, updatable = false)
    private LocalDate dataCriacao;
}
