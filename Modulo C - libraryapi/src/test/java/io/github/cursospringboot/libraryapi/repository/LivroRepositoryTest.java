package io.github.cursospringboot.libraryapi.repository;

import io.github.cursospringboot.libraryapi.model.Autor;
import io.github.cursospringboot.libraryapi.model.GeneroLivro;
import io.github.cursospringboot.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
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

  @Test
  public void salvarCascadeTest(){
      Livro livro = new Livro();
      livro.setIsbn("01234-56789");
      livro.setPreco(BigDecimal.valueOf(100));
      livro.setGenero(GeneroLivro.BIOGRAFIA);
      livro.setTitulo("Openheimer, o pai das explosões");
      livro.setDataPublicacao(LocalDate.of(2024,10,21));

      Autor autor = new Autor();
      autor.setNome("Warner Bross CO.");
      autor.setNacionalidade("Americano");
      autor.setDataNascimento(LocalDate.of(1988, 7, 10));

      livro.setAutor(autor);

      livroRepository.save(livro);
  }

  @Test
  public void listarTest(){
      List<Livro> livros = livroRepository.findAll();
      livros.forEach(System.out::println);
  }

  @Test
  public void atualizarTest(){
      UUID id = UUID.fromString("7345511f-26b6-4946-b2ce-41a088a034f1");

      Optional<Livro> livroSelecionado = livroRepository.findById(id);

      if (livroSelecionado.isPresent()){
          Livro livroAtualizado = livroSelecionado.get();

          System.out.println("Atualização dos Dados");
          System.out.println("Preco Anterior: " + livroAtualizado.getPreco());

          livroAtualizado.setPreco(BigDecimal.valueOf(105));

          livroRepository.save(livroAtualizado);

          System.out.println("Preco Atualizado: " + livroAtualizado.getPreco());
      }
  }

  @Test
  public void deletarTest(){
      UUID idLivro = UUID.fromString("40e939e5-9157-4848-99cf-c75b7390b1f9");
      livroRepository.deleteById(idLivro);
  }
}