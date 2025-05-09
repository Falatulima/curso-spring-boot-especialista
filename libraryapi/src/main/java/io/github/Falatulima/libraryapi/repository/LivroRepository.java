package io.github.Falatulima.libraryapi.repository;

import io.github.Falatulima.libraryapi.model.Autor;
import io.github.Falatulima.libraryapi.model.GeneroLivro;
import io.github.Falatulima.libraryapi.model.Livro;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

//ao pressionar alt+enter selecionando o nome da interface é possivel criar a classe de testes automaticamente
public interface LivroRepository extends JpaRepository<Livro, Long> {

    //Nesse findBy tem que conter o nome exato do atributo criado na entidade, nesse caso "AutorID" está presente na classe Livro.java.
    List<Livro> findByAutorID(Autor autor);
    List<Livro> findByTitulo(String titulo);
    //Optional<Livro> findByIsbn(String isbn); caso queira buscar e o livro talvez não exista
    List<Livro> findByIsbn(String isbn);
    List<Livro> findByTituloAndValor(String titulo, BigDecimal valor);
    List<Livro> findByTituloOrGenero(String titulo, GeneroLivro genero);
    //para ignorar o case sensitive
    List<Livro> findByTituloContainingIgnoreCaseOrGenero(String titulo, GeneroLivro genero);
    List<Livro> findByDataPublicacaoBetween(LocalDate inicio, LocalDate fim);

    @Query("select l from Livro l where l.genero = :genero order by :paramOrdenacao")
    List<Livro> findByGenero(@Param("genero") GeneroLivro generoLivro, @Param("paramOrdenacao") String ordenacao);

    //positional param, pega os parametros por posição 1,2,3...
    @Query("select l from Livro l where l.genero = ?1 order by ?2")
    List<Livro> findByGeneroPositionalParam(GeneroLivro generoLivro, String ordenacao);

    @Query("select l from Livro as l order by l.titulo, l.valor")
    List<Livro> listarTodosPorTituloValor();

    @Query("select a from Livro l join l.autorID a")
    List<Autor> listarAutoresComLivros();

    @Query("select distinct l.titulo from Livro l")
    List<String> listarNomeDiferentesLivros();

    @Query("select l.genero from Livro l join l.autorID a where a.nacionalidade = 'Brasileira' order by l.genero")
    List<String> listarGeneroAutoresBrasileiros();

    //SELECT l FROM Livro l → Vai buscar livros.
    //JOIN FETCH l.autorID → Vai carregar o autor junto.
    //WHERE l.autorID = :autor → Filtro: apenas livros escritos por determinado autor.
    @Query("SELECT l FROM Livro l JOIN FETCH l.autorID WHERE l.autorID = :autor")
    List<Livro> findByAutorIDComAutor(@Param("autor") Autor autor);

    //SELECT 1 FROM Livro 1 → Vai buscar livros da tabela.
    //JOIN FETCH l.autorID → Vai trazer junto os dados do autor (como nome, etc.), sem precisar de EAGER nem @Transactional.
    //WHERE l.titulo = :titulo → Filtro: só traz livros com o título informado.
    @Query("SELECT l FROM Livro l JOIN FETCH l.autorID WHERE l.titulo = :titulo")
    List<Livro> findByTituloComAutor(@Param("titulo") String titulo);

    @Modifying //para modificar registro, escrever, sobrescrever ou deletar
    @Transactional
    @Query("delete from Livro where genero = ?1")
    void deleteByGenero(GeneroLivro genero);

    @Modifying //para modificar registro, escrever, sobrescrever ou deletar
    @Transactional
    @Query("update Livro set autorID = ?1 where autorID = ?2")
    void alterarByAutorID(Autor id1, Autor id2);
}
