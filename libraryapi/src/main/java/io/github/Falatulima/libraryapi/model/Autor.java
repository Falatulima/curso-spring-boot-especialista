package io.github.Falatulima.libraryapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity //"Ei Spring, essa classe aqui vira uma tabela no banco de dados!"
@Table(name = "autor", schema = "public")
//get e set definidos pelo lombok, ao executar um clean instal o sistema ja gera os getters and setters no target
@Getter
@Setter
public class Autor {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //gerar id automáticamente
    private Long id;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "nacionalidade", length = 50, nullable = false)
    private String nacionalidade;

    //@OneToMany(mappedBy = "autorID")
    @Transient //ignorando livros
    private List<Livro> livros;
}
