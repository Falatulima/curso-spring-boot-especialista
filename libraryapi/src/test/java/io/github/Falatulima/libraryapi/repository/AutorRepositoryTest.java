package io.github.Falatulima.libraryapi.repository;

import io.github.Falatulima.libraryapi.model.Autor;
import io.github.Falatulima.libraryapi.model.GeneroLivro;
import io.github.Falatulima.libraryapi.model.Livro;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Autowired
    LivroRepository livroRepository;

    @Test
    public void salvarTest() {
        Autor autor = new Autor();
        autor.setNome("Julia");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(2000,9, 04));

        Optional<Autor> existente = repository.findByNomeAndDataNascimento(
                autor.getNome(),
                autor.getDataNascimento()
        );

        if(existente.isPresent()) {
            System.out.println("Autor já cadastrado no sistema!");
        } else {
            var autorSalvo = repository.save(autor);
            System.out.println("O autor: " + autorSalvo + "foi cadastrado com sucesso!");
        }
    }

    @Test
    public void atualizarTest() {
        var id = Long.valueOf(8);
        Optional<Autor> possivelAutor = repository.findById(id);

        if (possivelAutor.isPresent()) {
            Autor autorEncontrado = possivelAutor.get();
            System.out.println("Autor: ");
            System.out.println(autorEncontrado);

            autorEncontrado.setDataNascimento(LocalDate.of(2000,9, 04));

            repository.save(autorEncontrado);
        } else {
            System.out.println("Autor não existe");
        }
    }

    @Test
    public void contagemAutoresTest() {
        System.out.println("Número total de autores: " + repository.count());
    }

    @Test
    public void excluirTest() {
        var id = Long.valueOf(10);
        Optional<Autor> possivelAutor = repository.findById(id);

        if (possivelAutor.isPresent()) {
            Autor autorEncontrado = possivelAutor.get();
            System.out.println("Autor: ");
            System.out.println(autorEncontrado);

            repository.delete(autorEncontrado);
            System.out.println("Autor Excluido!");

            Optional<Autor> autorExcluido = repository.findById(id);
            if(autorExcluido.isEmpty()) {
                System.out.println("Autor não existe mais!");
            } else {
                System.out.println("Erro: Autor ainda existe!");
            }
        } else {
            System.out.println("Autor não existe!");
        }
    }

    @Test
    void salvarAutorComLivros() {
        Autor autor = new Autor();
        autor.setNome("Marcelo");
        autor.setNacionalidade("Americana");
        autor.setDataNascimento(LocalDate.of(1972,10, 18));

        Optional<Autor> existente = repository.findByNomeAndDataNascimento(
                autor.getNome(),
                autor.getDataNascimento()
        );

        if(existente.isPresent()) {
            System.out.println("Autor já cadastrado no sistema!");
            autor = existente.get();
        } else {
            var autorSalvo = repository.save(autor);
            System.out.println("O autor: " + autorSalvo.getNome() + " foi cadastrado com sucesso!");
        }
            Livro livro = new Livro();
            livro.setIsbn("34634-23423");
            livro.setValor(BigDecimal.valueOf(200));
            livro.setGenero(GeneroLivro.REFERENCIA);
            livro.setTitulo("Direito");
            livro.setDataPublicacao(LocalDate.of(2018, 1, 2));
            livro.setAutorID(autor);

            Livro livro2 = new Livro();
            livro2.setIsbn("34534-73565");
            livro2.setValor(BigDecimal.valueOf(300));
            livro2.setGenero(GeneroLivro.CIENCIA);
            livro2.setTitulo("A Ciencia do Direito");
            livro2.setDataPublicacao(LocalDate.of(2019, 2, 1));
            livro2.setAutorID(autor);

            livroRepository.saveAll(Arrays.asList(livro, livro2));

            System.out.println("Os livros: " +livro.getTitulo() +" e " + livro2.getTitulo() +" foram cadastrados com sucesso");
    }

    @Test
    //@Transactional
    void listarLivrosAutor() {
        Long id = Long.valueOf(12);
        var autor = repository.findById(id).get();

        //utilizando FETCH para trazer o nome do autor
        //List<Livro> livrosLista = livroRepository.findByAutorIDComAutor(autor);

        List<Livro> livrosLista = livroRepository.findByAutorID(autor);

        autor.setLivros(livrosLista);
        autor.getLivros().forEach(System.out::println);
    }
}
