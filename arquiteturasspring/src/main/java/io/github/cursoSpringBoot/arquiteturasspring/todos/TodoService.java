package io.github.cursoSpringBoot.arquiteturasspring.todos;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TodoService {

    public TodoRepository repository;
    public TodoValidator validator;
    public MailSender mailSender;

    public TodoService(TodoRepository todoRepository,
                       TodoValidator todoValidator,
                       MailSender mailSender) {
        this.repository = todoRepository;
        this.validator = todoValidator;
        this.mailSender = mailSender;
    }

    public TodoEntity salvar(TodoEntity newTodo) {
        validator.validar(newTodo);
        return repository.save(newTodo);
    }

    public void atualizarStatus(TodoEntity todo) {
        String status = todo.isConcluido() ? "Concluído" : "Não concluído";
        mailSender.enviar("Foi atualizado para: " + status);
        repository.save(todo);
    }

    public TodoEntity buscarPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo não encontrado"));
    }

}
