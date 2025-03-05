package io.github.cursospringboot.libraryapi.service;

import io.github.cursospringboot.libraryapi.model.Autor;
import io.github.cursospringboot.libraryapi.model.GeneroLivro;
import io.github.cursospringboot.libraryapi.model.Livro;
import io.github.cursospringboot.libraryapi.repository.AutorRepository;
import io.github.cursospringboot.libraryapi.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class TransactionService {
    @Autowired
    LivroRepository livroRepository;

    @Autowired
    AutorRepository autorRepository;

    @Transactional
    public void executar(){
        Autor autor = new Autor();
        autor.setNome("Dan Hartel");
        autor.setNacionalidade("Americano");
        autor.setDataNascimento(LocalDate.of(1985, 2, 15));

        autorRepository.save(autor);

        Livro livro = new Livro();
        livro.setIsbn("28576-19237");
        livro.setPreco(BigDecimal.valueOf(115));
        livro.setGenero(GeneroLivro.BIOGRAFIA);
        livro.setTitulo("Buy Back your Time");
        livro.setDataPublicacao(LocalDate.of(2020,9,11));
        livro.setAutor(autor);

        livroRepository.save(livro);

        if (autor.getNome().equals("Dan Hartel")){
            throw new RuntimeException("Rollback!");
        }
    }
}
