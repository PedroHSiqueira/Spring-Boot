package io.github.phasiqueiras.convidados.controller;

import io.github.phasiqueiras.convidados.model.Convidado;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class ConvidadosController {

    @GetMapping
    public List<Convidado> getConvidados(){
        List<Convidado> listaConvidados = new ArrayList<Convidado>();
        listaConvidados.add(new Convidado("Pedro", "777.777.777-77"));
        listaConvidados.add(new Convidado("Henrique", "888.888.888-88"));

        return listaConvidados;
    }
}
