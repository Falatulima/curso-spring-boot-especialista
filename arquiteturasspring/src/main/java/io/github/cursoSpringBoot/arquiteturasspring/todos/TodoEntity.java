package io.github.cursoSpringBoot.arquiteturasspring.todos;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_to")
public class TodoEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //valor gerado automáticamente no banco por auto incremento
    private  Integer id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "fl_concluido")
    private boolean concluido;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isConcluido() {
        return concluido;
    }

    public void setConcluido(boolean concluido) {
        this.concluido = concluido;
    }
}
