package io.github.Falatulima.libraryapi.repository;

import io.github.Falatulima.libraryapi.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Test
    public void salvarTest() {
        Autor autor = new Autor();
        autor.setNome("Julia");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1999,9, 04));

        var autorSalvo = repository.save(autor);
        System.out.println("Autor Salvo: "+ autorSalvo);
    }

    @Test
    public void atualizarTest() {
        var id = Long.valueOf(3);
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
        var id = Long.valueOf(3);
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
}
