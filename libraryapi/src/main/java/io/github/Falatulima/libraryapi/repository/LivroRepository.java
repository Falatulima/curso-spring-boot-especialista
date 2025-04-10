package io.github.Falatulima.libraryapi.repository;

import io.github.Falatulima.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

//ao pressionar alt+enter selecionando o nome da interface é possivel criar a classe de testes automaticamente
public interface LivroRepository extends JpaRepository<Livro, Long> {
}
