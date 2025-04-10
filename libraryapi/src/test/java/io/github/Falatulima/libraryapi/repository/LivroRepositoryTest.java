package io.github.Falatulima.libraryapi.repository;

import io.github.Falatulima.libraryapi.model.Autor;
import io.github.Falatulima.libraryapi.model.GeneroLivro;
import io.github.Falatulima.libraryapi.model.Livro;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository repository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvarTest() {
        Livro livro = new Livro();
        livro.setIsbn("10203-28328");
        livro.setValor(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("UFO");
        livro.setDataPublicacao(LocalDate.of(1989, 1, 2));

        Autor autor = autorRepository.findById(Long.valueOf(1))
                .orElse(null);

        livro.setAutorID(autor);

        var livroSalvo = repository.save(livro);

        System.out.println("Livro salvo com sucesso: " + livroSalvo);
    }
}