package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Usuario {

    @Id
    @Column(name = "id_usuario", updatable = false)
    private UUID id = UUID.randomUUID();

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
