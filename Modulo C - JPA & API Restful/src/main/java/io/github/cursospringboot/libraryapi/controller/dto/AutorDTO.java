package io.github.cursospringboot.libraryapi.controller.dto;

import io.github.cursospringboot.libraryapi.model.Autor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

//Data-Transfer-Object
public record AutorDTO(
        UUID id,
        @NotBlank(message = "Campo Obrigatório")
        @Size(min = 5, max = 100, message = "Fora do tamanho padrão")
        String nome,
        @NotNull(message = "Campo Obrigatório")
        @Past(message = "Não pode ser uma data futura")
        LocalDate dataNascimento,
        @NotBlank(message = "Campo Obrigatório")
        @Size(min = 5, max = 50, message = "Fora do tamanho padrão")
        String nacionalidade) {

    public Autor mapearAutor(){
        Autor autor = new Autor();
        autor.setNome(this.nome);
        autor.setDataNascimento(this.dataNascimento);
        autor.setNacionalidade(this.nacionalidade);
        return autor;
    }
}
