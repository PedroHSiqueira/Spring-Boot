package io.github.cursospringboot.libraryapi.repository;

import io.github.cursospringboot.libraryapi.model.Autor;
import io.github.cursospringboot.libraryapi.model.GeneroLivro;
import io.github.cursospringboot.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
public class LivroRepositoryTest {

  @Autowired
  LivroRepository livroRepository;

  @Autowired
  AutorRepository autorRepository;

  @Test
  public void salvarTest(){
      Livro livro = new Livro();
      livro.setIsbn("98765-43210");
      livro.setPreco(BigDecimal.valueOf(100));
      livro.setGenero(GeneroLivro.CIENCIA);
      livro.setTitulo("Big Bang");
      livro.setDataPublicacao(LocalDate.of(2001,9,11));

      //Busca um Autor Existente ou devolve Nulo
      Autor autor = autorRepository.findById(UUID.fromString("82e0efac-177a-4de6-a2d9-cf98f29c7449")).orElse(null);

      livro.setAutor(autor);

      livroRepository.save(livro);
  }
}