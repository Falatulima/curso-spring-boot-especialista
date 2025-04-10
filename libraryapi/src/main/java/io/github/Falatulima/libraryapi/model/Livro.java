package io.github.Falatulima.libraryapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity //"Ei Spring, essa classe aqui vira uma tabela no banco de dados!"
@Table(name = "livro", schema = "public")
@Data
//Data engloba as seguintes anotações:
//@Getter @Setter
//@ToString
//@EqualsAndHashCode
//@RequiredArgsConstructor
public class Livro {

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

    @ManyToOne
    @JoinColumn(name = "id_autor")
    private Autor autorID;

}
