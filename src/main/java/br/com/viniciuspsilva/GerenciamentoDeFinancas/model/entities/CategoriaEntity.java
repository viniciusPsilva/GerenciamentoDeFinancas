package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity(name = "tbl02_categoria")
public class CategoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false, updatable = false)
    private LocalDate dataCriacao = LocalDate.now();

    @Column
    private LocalDate dataAtualizacao = LocalDate.now();

    @Column(updatable = false)
    private String idUsuario;

}
