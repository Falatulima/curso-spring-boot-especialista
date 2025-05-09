package io.github.Falatulima.libraryapi.repository;

import io.github.Falatulima.libraryapi.model.Autor;
import io.github.Falatulima.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    Optional<Autor> findByNomeAndDataNascimento(String nome, LocalDate dataNascimento);

}
