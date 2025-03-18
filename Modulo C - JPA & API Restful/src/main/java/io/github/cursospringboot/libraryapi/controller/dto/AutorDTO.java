package io.github.cursospringboot.libraryapi.controller.dto;

import io.github.cursospringboot.libraryapi.model.Autor;

import java.time.LocalDate;
import java.util.UUID;

//Data-Transfer-Object
public record AutorDTO(UUID id, String nome, LocalDate dataNascimento, String nacionalidade) {

    public Autor mapearAutor(){
        Autor autor = new Autor();
        autor.setNome(this.nome);
        autor.setDataNascimento(this.dataNascimento);
        autor.setNacionalidade(this.nacionalidade);
        return autor;
    }
}
