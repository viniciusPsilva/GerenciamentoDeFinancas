package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Integer id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = true)
    private String descricao;

    @OneToMany(orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Gasto> gastos;

}
