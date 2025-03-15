package io.github.cursospringboot.libraryapi.controller;

import io.github.cursospringboot.libraryapi.controller.dto.AutorDTO;
import io.github.cursospringboot.libraryapi.model.Autor;
import io.github.cursospringboot.libraryapi.repository.AutorRepository;
import io.github.cursospringboot.libraryapi.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autores")
public class AutorController {

    @Autowired
    AutorService autorService;

    @PostMapping
    private ResponseEntity salvar(@RequestBody AutorDTO autor){
        return new ResponseEntity("Autor Salvo com Sucesso! " + autor, HttpStatus.CREATED);
    }

    @GetMapping
    private List<Autor> listarAutores(){
        return autorService.getAutores();
    }
}
