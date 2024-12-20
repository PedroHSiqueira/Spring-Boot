package com.arquitetura.arquiteturaspring.montadora.configuration;

import com.arquitetura.arquiteturaspring.montadora.Motor;
import com.arquitetura.arquiteturaspring.montadora.TipoMotor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MontadoraConfiguration {

    @Bean(name = "motorAspirado")
    public Motor motorAspirado(){
        Motor motor = new Motor();
        motor.setCavalos(126);
        motor.setCilindros(4);
        motor.setModelo("1.5L VTEC Flex");
        motor.setLitragem(1.5);
        motor.setTipoMotor(TipoMotor.ASPIRADO);
        return motor;
    }

    @Bean(name = "motorEletrico")
    public Motor motorEletrico(){
        Motor motor = new Motor();
        motor.setCavalos(531);
        motor.setCilindros(0);
        motor.setModelo("82,5 KW");
        motor.setLitragem(0.0);
        motor.setTipoMotor(TipoMotor.ELETRICO);
        return motor;
    }

    @Bean(name = "motorTurbo")
    @Primary
    public Motor motorTurbo(){
        Motor motor = new Motor();
        motor.setCavalos(178);
        motor.setCilindros(4);
        motor.setModelo("1.2L VTEC Turbo Flex");
        motor.setLitragem(1.2);
        motor.setTipoMotor(TipoMotor.TURBO);
        return motor;
    }
}
