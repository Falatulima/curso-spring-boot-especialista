package io.github.Falatulima.libraryapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity //"Ei Spring, essa classe aqui vira uma tabela no banco de dados!"
@Table(name = "livro", schema = "public")
@Data
@ToString(exclude = "autorID")
//Data engloba as seguintes anotações:
//@Getter @Setter
//@ToString
//@EqualsAndHashCode
//@RequiredArgsConstructor
public class Livro {

/*    @Override
    public String toString() {
        return "Livro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", isbn='" + isbn + '\'' +
                ", dataPublicacao=" + dataPublicacao +
                ", genero=" + genero +
                ", valor=" + valor +
                //operador ternário: condição ? valorSeVerdadeiro : valorSeFalso
                ", autor=" + (autorID != null ? autorID.getNome() : "null") +
                '}';
    }*/

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "isbn", length = 20, nullable = false)
    private String isbn;

    @Column(name = "titulo", length = 150, nullable = false)
    private String titulo;

    @Column(name = "data_publicacao", nullable = false)
    private LocalDate dataPublicacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "genero", length = 30, nullable = false)
    private GeneroLivro genero;

    @Column(name = "valor", precision = 18, scale = 2)
    private BigDecimal valor;

    @ManyToOne(
            //cascade = CascadeType.ALL,
            //fetch = FetchType.EAGER //Para trazer os dados da tabela e realizar um inner nas tabelas vinculadas, no caso os dados do autor.
            fetch = FetchType.LAZY //Para trazer apenas os dados da tebela livro.
    )
    @JoinColumn(name = "id_autor")
    private Autor autorID;
}
