package com.arquitetura.arquiteturaspring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ExemploValue {

    //Recuperando do Yml
    @Value("${app.config.variavel}")
    private String variavel;

    public void imprimirVariavel(){
        System.out.println(this.variavel);
    }
}
