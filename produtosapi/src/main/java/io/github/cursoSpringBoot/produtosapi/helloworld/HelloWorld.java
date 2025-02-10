package io.github.cursoSpringBoot.produtosapi;

import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.GetMapping;

public class HelloWorld {

    @GetMapping("/hello-world")
    public String hellotWorld(){
        return "Hello World!";
    }

    public static void main(String[] args) {
        SpringApplication.run(ProdutosApiApplication.class, args);
    }

}
