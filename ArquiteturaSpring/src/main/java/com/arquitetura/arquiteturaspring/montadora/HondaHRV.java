package com.arquitetura.arquiteturaspring.montadora;

import java.awt.*;

public class HondaHRV extends Carro{

    public HondaHRV(Motor motor) {
        super(motor);
        setModelo("HRV");
        setCor(Color.RED);
        setMontadora(Montadora.HONDA);
    }
}
