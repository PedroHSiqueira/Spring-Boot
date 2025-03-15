package io.github.cursospringboot.libraryapi.controller.dto;

import java.time.LocalDate;

//Data-Transfer-Object
public record AutorDTO(String nome, LocalDate dataNascimento, String nacionalidade) {
}
