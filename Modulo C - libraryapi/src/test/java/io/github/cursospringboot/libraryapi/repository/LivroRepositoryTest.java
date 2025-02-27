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
  public void listarLivroAutorTest(){
      UUID id = UUID.fromString("7345511f-26b6-4946-b2ce-41a088a034f1");
      Livro livro = livroRepository.findById(id).orElse(null);

      if (livro != null){
          System.out.println("Livro: " + livro.getTitulo());
          System.out.println("Autor: " + livro.getAutor().getNome());
      }
  }

  @Test
  public void atualizarTest(){
      UUID id = UUID.fromString("7345511f-26b6-4946-b2ce-41a088a034f1");

      Livro livroSelecionado = livroRepository.findById(id).orElse(null);

      if (livroSelecionado != null){

          System.out.println("Atualização dos Dados");
          System.out.println("Preco Anterior: " + livroSelecionado.getPreco());

          livroSelecionado.setPreco(BigDecimal.valueOf(105));

          livroRepository.save(livroSelecionado);

          System.out.println("Preco Atualizado: " + livroSelecionado.getPreco());
      }
  }

  @Test
  public void atualizarAutorTest(){
      UUID id = UUID.fromString("7345511f-26b6-4946-b2ce-41a088a034f1");
      Optional<Livro> livroParaAtualizar = livroRepository.findById(id);

      UUID idAutor = UUID.fromString("f0540d61-51ac-43a6-923a-0c7f2d55a27f");
      Optional<Autor> novoAutor = autorRepository.findById(idAutor);

      if (livroParaAtualizar.isPresent() && novoAutor.isPresent()){
          Livro livroBanco = livroParaAtualizar.get();
          livroBanco.setAutor(novoAutor.get());
          livroRepository.save(livroBanco);
      }
  }

  @Test
  public void deletarTest(){
      UUID idLivro = UUID.fromString("40e939e5-9157-4848-99cf-c75b7390b1f9");
      livroRepository.deleteById(idLivro);
  }

  @Test
    public void listarLivrosAutorTest(){
      UUID uuid = UUID.fromString("ac1c7b3e-c993-4ba8-90b3-6a38b7db6562");
      Autor autor = autorRepository.findById(uuid).get();

      List<Livro> livrosAutor = livroRepository.findByAutor(autor);
      autor.setLivros(livrosAutor);

      autor.getLivros().forEach(System.out::println);
  }

  @Test
  public void buscarLivroPorTituloTest(){
      List<Livro> livros = livroRepository.findByTitulo("Buy Back your Time");
      livros.forEach(System.out::println);
  }

  @Test
  public void buscarLivroPorIsbnTest(){
      List<Livro> livros = livroRepository.findByIsbn("28576-19237");
      livros.forEach(System.out::println);
  }

  @Test
  public void buscarLivroPorTituloPrecoTest(){
      List<Livro> livros = livroRepository.findByTituloAndPreco("Buy Back your Time", BigDecimal.valueOf(115.00));
      livros.forEach(System.out::println);
  }

  @Test
  public void buscarLivroPorTituloOuIsbnTest(){
      List<Livro> livros = livroRepository.findByTituloOrIsbn("Buy Back your Time", "28576-19237");
      livros.forEach(System.out::println);
  }

}