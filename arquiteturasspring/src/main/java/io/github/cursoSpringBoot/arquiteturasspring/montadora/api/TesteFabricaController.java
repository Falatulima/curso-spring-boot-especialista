package io.github.cursoSpringBoot.arquiteturasspring.montadora.api;

import io.github.cursoSpringBoot.arquiteturasspring.montadora.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carros")
public class TesteFabricaController {

    @Autowired //vai "buscar" na classe montadoraConfiguration o motor que foi estanciado
    private Motor motor;

    @PostMapping
    public CarroStatus iniciarCarro(@RequestBody Chave chave) {
        var carro = new HondaHRV(motor);
        return carro.darIgnicao(chave);
    }
}
