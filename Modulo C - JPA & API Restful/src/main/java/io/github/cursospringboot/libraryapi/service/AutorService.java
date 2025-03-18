package io.github.cursospringboot.libraryapi.service;

import io.github.cursospringboot.libraryapi.model.Autor;
import io.github.cursospringboot.libraryapi.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AutorService {

    @Autowired
    AutorRepository autorRepository;

    public Autor salvar(Autor autor){
        return autorRepository.save(autor);
    }

    public Optional<Autor> obterPorId (UUID id){
        return autorRepository.findById(id);
    }

    public List<Autor> getAutores(){
        return autorRepository.findAll();
    }

    public void deletar(Autor autor){
        autorRepository.delete(autor);
    }
}
