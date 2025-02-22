package io.github.cursoSpringBoot.arquiteturasspring.todos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Integer> {

    Boolean existsByDescricao(String descricao);
}
