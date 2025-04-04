package io.github.cursospringboot.libraryapi.controller;

import io.github.cursospringboot.libraryapi.controller.dto.CadastroLivroDTO;
import io.github.cursospringboot.libraryapi.controller.dto.ErroResposta;
import io.github.cursospringboot.libraryapi.exceptions.RegistroDuplicadoException;
import io.github.cursospringboot.libraryapi.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/livros")
@RequiredArgsConstructor
public class LivroController {
    private final LivroService livroService;

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody @Valid CadastroLivroDTO dto){
        try {
            return ResponseEntity.ok(dto);
        }catch (RegistroDuplicadoException e){
            ErroResposta erroDTO = ErroResposta.conflito(e.getMessage());
            return ResponseEntity.status(erroDTO.status()).body(erroDTO);
        }
    }
}
