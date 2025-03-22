package io.github.cursospringboot.libraryapi.controller.dto;

import io.github.cursospringboot.libraryapi.model.Autor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

//Data-Transfer-Object
public record AutorDTO(UUID id, @NotBlank(message = "Campo Obrigatório") String nome, @NotNull(message = "Campo Obrigatório") LocalDate dataNascimento, @NotBlank(message = "Campo Obrigatório") String nacionalidade) {

    public Autor mapearAutor(){
        Autor autor = new Autor();
        autor.setNome(this.nome);
        autor.setDataNascimento(this.dataNascimento);
        autor.setNacionalidade(this.nacionalidade);
        return autor;
    }
}
