package io.github.cursospringboot.libraryapi.repository;

import io.github.cursospringboot.libraryapi.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
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
    public void atualizaAutor(){
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
}
