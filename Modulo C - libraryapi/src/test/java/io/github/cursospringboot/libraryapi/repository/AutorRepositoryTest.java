package io.github.cursospringboot.libraryapi.repository;

import io.github.cursospringboot.libraryapi.model.Autor;
import io.github.cursospringboot.libraryapi.model.GeneroLivro;
import io.github.cursospringboot.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository autorRepository;

    @Autowired
    LivroRepository livroRepository;

    @Test
    public void salvarTeste(){
        Autor autor = new Autor();
        autor.setNome("Universal");
        autor.setNacionalidade("Americano");
        autor.setDataNascimento(LocalDate.of(1915, 8, 12));

        Autor autorSalvo = autorRepository.save(autor);
        System.out.println("Autor Salvo: " + autorSalvo);
    }

    @Test
    public void atualizaTest(){
        UUID id = UUID.fromString("f3d440bf-8e39-4066-8c63-14b9141326e3");

        Optional<Autor> possivelAutor = autorRepository.findById(id);

        if (possivelAutor.isPresent()){
            Autor autorEncontrado = possivelAutor.get();
            System.out.println("Dados do autor: ");
            System.out.println(autorEncontrado);

            autorEncontrado.setDataNascimento(LocalDate.of(1980,8,12));

            autorRepository.save(autorEncontrado);
        }
    }

    @Test
    public void listarTest(){
        List<Autor> autores = autorRepository.findAll();
        autores.forEach(System.out::println);
    }

    @Test
    public void countTest(){
        long contagem = autorRepository.count();
        System.out.println("Numero de Registros: " + contagem);
    }

    @Test
    public void deletePorIdTest(){
        UUID id = UUID.fromString("f3d440bf-8e39-4066-8c63-14b9141326e3");
        autorRepository.deleteById(id);
    }

    @Test
    public void deleteTest(){
        UUID id = UUID.fromString("315dae6f-9b7b-40e5-8503-eae61bd91306");
        Autor autorSelecionado = autorRepository.findById(id).get();

        autorRepository.delete(autorSelecionado);
    }

    @Test
    public void salvarAutorComLivros(){
        Autor autor = new Autor();
        autor.setNome("Dan Hartel");
        autor.setNacionalidade("Americano");
        autor.setDataNascimento(LocalDate.of(1985, 2, 15));

        Livro livro = new Livro();
        livro.setIsbn("28576-19237");
        livro.setPreco(BigDecimal.valueOf(115));
        livro.setGenero(GeneroLivro.BIOGRAFIA);
        livro.setTitulo("Buy Back your Time");
        livro.setDataPublicacao(LocalDate.of(2020,9,11));
        livro.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("24178-98734");
        livro2.setPreco(BigDecimal.valueOf(98));
        livro2.setGenero(GeneroLivro.BIOGRAFIA);
        livro2.setTitulo("Software as a service");
        livro2.setDataPublicacao(LocalDate.of(2024,11,7));
        livro2.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);
        autor.getLivros().add(livro2);

        autorRepository.save(autor);

        livroRepository.saveAll(autor.getLivros());

    }
}
