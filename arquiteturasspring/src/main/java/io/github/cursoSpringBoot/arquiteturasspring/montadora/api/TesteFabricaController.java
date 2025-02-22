package io.github.cursoSpringBoot.arquiteturasspring.montadora.api;

import io.github.cursoSpringBoot.arquiteturasspring.montadora.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carros")
public class TesteFabricaController {

    @Autowired //vai "buscar" na classe montadoraConfiguration o motor que foi estanciado
    @Eletrico
    private Motor motor;

    @PostMapping
    public CarroStatus iniciarCarro2(@RequestBody Chave chave) {
        var carro = new NissanSkyline(motor);
        return carro.darIgnicao(chave);
    }



}
