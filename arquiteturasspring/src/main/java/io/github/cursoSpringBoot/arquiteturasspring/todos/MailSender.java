package io.github.cursoSpringBoot.arquiteturasspring.todos;

import org.springframework.stereotype.Component;

@Component
public class MailSender {

    public void enviar(String mensagem) {
        System.out.println("Email enviado: " + mensagem);
    }
}
