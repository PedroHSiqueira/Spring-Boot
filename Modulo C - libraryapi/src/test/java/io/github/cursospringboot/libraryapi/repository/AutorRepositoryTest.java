package io.github.cursospringboot.libraryapi.repository;

import io.github.cursospringboot.libraryapi.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository autorRepository;

    @Test
    public void salvarTeste(){
        Autor autor = new Autor();
        autor.setNome("Maria de Freitas");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(1968, 8, 12));

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
}
