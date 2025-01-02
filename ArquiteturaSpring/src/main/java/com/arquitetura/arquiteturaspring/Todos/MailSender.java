package com.arquitetura.arquiteturaspring.Todos;

import org.springframework.stereotype.Component;

@Component
public class MailSender {

    public void enviarEmail(String mensagem){
        System.out.println("Email enviado: " + mensagem);
    }
}
