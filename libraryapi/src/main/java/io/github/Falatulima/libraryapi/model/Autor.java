package io.github.Falatulima.libraryapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity //"Ei Spring, essa classe aqui vira uma tabela no banco de dados!"
@Table(name = "autor", schema = "public")
//get e set definidos pelo lombok, ao executar um clean instal o sistema ja gera os getters and setters no target
@Getter
@Setter
@ToString(exclude = "livros")
public class Autor {

/*    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", nacionalidade='" + nacionalidade + '\'' +
                '}';
    }*/

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

    @OneToMany(mappedBy = "autorID", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //@Transient //ignorando livros no cadastro de autor com livros
    private List<Livro> livros;
}
