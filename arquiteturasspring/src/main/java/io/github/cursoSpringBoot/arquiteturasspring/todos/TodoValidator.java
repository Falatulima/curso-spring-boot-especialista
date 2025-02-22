package io.github.cursoSpringBoot.arquiteturasspring.todos;

import org.springframework.stereotype.Component;

@Component
public class TodoValidator {

    private TodoRepository repository;

    public TodoValidator(TodoRepository repository) {
        this.repository = repository;
    }

    public void validar(TodoEntity todo) {
        if(existeTodoComDesc(todo.getDescricao())) {
            throw new IllegalArgumentException("Ja existe um TODO com essa descrição no sistema");
        }
    }

    public boolean existeTodoComDesc(String descricao) {
        return repository.existsByDescricao(descricao);
    }
}
