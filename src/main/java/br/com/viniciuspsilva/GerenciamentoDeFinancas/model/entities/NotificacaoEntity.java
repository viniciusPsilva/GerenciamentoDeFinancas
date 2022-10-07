package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class NotificacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notificacao", updatable = false)
    private Long id;

    @Column(nullable = false, length = 60)
    private String titulo;

    @Column(nullable = false)
    private String mensagem;

    @Column(nullable = false, updatable = false)
    private LocalDate dataCriacao;

    @ManyToOne(targetEntity = UsuarioEntity.class)
    @JoinColumn(referencedColumnName = "id_usuario")
    private UsuarioEntity usuarioEntity;
}
