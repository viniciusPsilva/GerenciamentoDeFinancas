package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class CategoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Integer id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = true)
    private String descricao;

    @Column(nullable = false, updatable = false)
    private LocalDate dataCriacao = LocalDate.now();

}
