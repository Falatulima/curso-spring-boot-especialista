package io.github.cursoSpringBoot.arquiteturasspring.montadora.configuration;

import io.github.cursoSpringBoot.arquiteturasspring.montadora.Motor;
import io.github.cursoSpringBoot.arquiteturasspring.montadora.TipoMotor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MontadoraConfiguration {

    @Bean
    public Motor motor() {
        var motor = new Motor();
        motor.setCavalos(150);
        motor.setCilindros(4);
        motor.setModelo("XPTO-0");
        motor.setLitragem(1.8);
        motor.setTipo(TipoMotor.ASPIRADO);
        return  motor;
    }

    @Bean
    public Motor motorEletrico() {
        var motor = new Motor();
        motor.setCavalos(250);
        motor.setCilindros(0);
        motor.setModelo("ELE-0");
        motor.setLitragem(0);
        motor.setTipo(TipoMotor.ELETRICO);
        return  motor;
    }

    @Bean
    public Motor motorTurbo() {
        var motor = new Motor();
        motor.setCavalos(350);
        motor.setCilindros(4);
        motor.setModelo("TURB-0");
        motor.setLitragem(2.0);
        motor.setTipo(TipoMotor.TURBO);
        return  motor;
    }
}
