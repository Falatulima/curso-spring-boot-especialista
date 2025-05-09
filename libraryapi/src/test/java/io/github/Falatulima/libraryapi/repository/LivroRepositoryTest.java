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
import java.util.List;

/**
 * see {@link LivroRepository}
 */

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository repository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvarTest() {
        Livro livro = new Livro();
        livro.setIsbn("34634-23423");
        livro.setValor(BigDecimal.valueOf(200));
        livro.setGenero(GeneroLivro.REFERENCIA);
        livro.setTitulo("Direito");
        livro.setDataPublicacao(LocalDate.of(2018, 1, 2));

        Autor autor = autorRepository.findById(Long.valueOf(11))
                .orElse(null);

        livro.setAutorID(autor);

        var livroSalvo = repository.save(livro);

        System.out.println("Livro salvo com sucesso: " + livroSalvo);
    }

    @Test
    void cascadeTest() {
        Livro livro = new Livro();
        livro.setIsbn("34534-73565");
        livro.setValor(BigDecimal.valueOf(150));
        livro.setGenero(GeneroLivro.CIENCIA);
        livro.setTitulo("A Ciencia do Direito");
        livro.setDataPublicacao(LocalDate.of(2019, 02, 01));

        var possivelAutor = autorRepository.findByNomeAndDataNascimento("Marcelo", LocalDate.of(1972,10, 18));

        if (possivelAutor.isPresent()) {
            var autorEncontrado = possivelAutor.get();
            System.out.println("Autor: " + autorEncontrado);
            livro.setAutorID(autorEncontrado);
        } else {
            Autor autor = new Autor();
            autor.setNome("Marcelo");
            autor.setNacionalidade("Americana");
            autor.setDataNascimento(LocalDate.of(1972,10, 18));

            livro.setAutorID(autor);
        }

        var livroSalvo = repository.save(livro);

        System.out.println("Autor do livro salvo com sucesso: " + livroSalvo.getAutorID());
        System.out.println("Livro salvo com sucesso: " + livroSalvo);
    }

    @Test
    void alterarAutorLivro() {
        var livroParaAtualizar = repository.findById(Long.valueOf(1));

        if (livroParaAtualizar.isPresent()) {
            var livro = livroParaAtualizar.get();
            System.out.println("Livro: ");
            System.out.println(livro);

            var possivelAutor = autorRepository.findById(Long.valueOf(1));

            if (possivelAutor.isPresent()) {
                var autorEncontrado = possivelAutor.get();
                System.out.println("Autor: " + autorEncontrado);
                livro.setAutorID(autorEncontrado);

                //não esqueça de salvar o livro após alteração
                var livroSalvo = repository.save(livro);

                System.out.println("ID do autor alterado: " + livroSalvo.getAutorID().getNome());
                System.out.println("Livro salvo com sucesso: " + livroSalvo);
            } else {
                System.out.println("Autor não existe no sistema!");
            }
        } else {
            System.out.println("Livro não encontrado!");
        }
    }

    @Test //caso o cascade estiver descomentado na classe livro, irá apagar o livro e o autor vinculado.
    void deletarCascade() {
        Long id = Long.valueOf(7);
        repository.deleteById(id);
    }

    @Test
    @Transactional //Quando houver um "FetchType.LAZY" no objeto instanciado na classe principal, neste caso "Livro.java".
    void buscarLivroTest() {
        Long id = Long.valueOf(1);
        Livro livro = repository.findById(id).orElse(null);
        System.out.println("Livro: " + livro.getTitulo());
        System.out.println("Autor: " + livro.getAutorID().getNome());
    }

    @Test
    void pesquisarTituloTest() {
        //utilizando FETCH para trazer o nome do autor
        //List<Livro> lista = repository.findByTituloComAutor("UFO");

        List<Livro> lista = repository.findByTitulo("UFO");
        lista.forEach(System.out::println);
    }

    @Test
    void pesquisarPorIsbnTest() {
        List<Livro> lista = repository.findByIsbn("10203-28328");
        lista.forEach(System.out::println);
    }

    @Test
    void pesquisarPorTituloValorTest() {
        var valor = new BigDecimal(100.00);
        List<Livro> lista = repository.findByTituloAndValor("UFO", valor);
        lista.forEach(System.out::println);
    }

    @Test
    void pesquisarPorTituloOuGeneroTest() {
        var genero = GeneroLivro.CIENCIA;
        List<Livro> lista = repository.findByTituloOrGenero("UFO", genero);
        lista.forEach(System.out::println);
    }

    @Test
    void pesquisasEntreDataTest() {
        LocalDate inicio = LocalDate.of(1989, 02, 01);
        LocalDate fim = LocalDate.of(2020, 01, 02);
        List<Livro> lista = repository.findByDataPublicacaoBetween( inicio, fim);
        lista.forEach(System.out::println);
    }

    @Test
    void listaPorTituloValorJPQLTest() {
        var resultado = repository.listarTodosPorTituloValor();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarAutoresDosLivros() {
       //var resultado = repository.listarAutoresComLivros();
        //resultado.forEach(System.out::println);
    }

    @Test
    void listarTitulosNaoRepeditosTest() {
        var resultado = repository.listarNomeDiferentesLivros();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarGenerosAutoresBrTest() {
        var resultado = repository.listarGeneroAutoresBrasileiros();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarLivrosPorGeneroOrdenado() {
        var resultado = repository.findByGenero(GeneroLivro.MISTERIO, "valor");
        resultado.forEach(System.out::println);
    }

    @Test
    void listarLivrosPorPositionalParam() {
        var resultado = repository.findByGeneroPositionalParam(GeneroLivro.MISTERIO, "valor");
        resultado.forEach(System.out::println);
    }

    @Test
    void deletePorGeneroTest() {
        repository.deleteByGenero(GeneroLivro.CIENCIA);
    }

    @Test
    void alterarAutorTest() {

        Autor autorAntigo = new Autor();
        autorAntigo.setId(1L); // ID do autor atual nos livros

        Autor novoAutor = new Autor();
        novoAutor.setId(4L);
        repository.alterarByAutorID(autorAntigo,
                novoAutor);
    }
}