package io.github.cursoSpringBoot.produtosapi.helloworld;

import io.github.cursoSpringBoot.produtosapi.ProdutosApiApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class HelloWorld {

    @GetMapping("/hello-world")
    public String helloWorld(){
        return "Hello World!";
    }

    public static void main(String[] args) {
        SpringApplication.run(ProdutosApiApplication.class, args);
    }

}
