package io.github.cursoSpringBoot.arquiteturasspring.montadora;

import java.awt.*;

public class NissanSkyline extends Carro {

    public NissanSkyline(Motor motor) {
        super(motor);
        setModelo("Skyline");
        setCor(Color.BLUE);
        setMontadora(Montadora.NISSAN);
    }
}
