package io.github.cursospringboot.libraryapi.controller;

import io.github.cursospringboot.libraryapi.service.LivroService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/livro")
@RequiredArgsConstructor
public class LivroController {
    private final LivroService livroService;
}
