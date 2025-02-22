package io.github.cursoSpringBoot.arquiteturasspring.montadora.configuration;

import io.github.cursoSpringBoot.arquiteturasspring.montadora.Motor;
import io.github.cursoSpringBoot.arquiteturasspring.montadora.TipoMotor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MontadoraConfiguration {

    @Bean(name = "motorAspirado")
    public Motor motorAspirado() {
        var motor = new Motor();
        motor.setCavalos(150);
        motor.setCilindros(4);
        motor.setModelo("XPTO-0");
        motor.setLitragem(1.8);
        motor.setTipo(TipoMotor.ASPIRADO);
        return  motor;
    }

    @Bean(name = "motorEletrico")
    public Motor motorEletrico() {
        var motor = new Motor();
        motor.setCavalos(250);
        motor.setCilindros(0);
        motor.setModelo("ELE-0");
        motor.setLitragem(0.0);
        motor.setTipo(TipoMotor.ELETRICO);
        return  motor;
    }

    @Bean(name = "motorTurbo")
    @Primary
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
