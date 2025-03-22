package io.github.cursospringboot.libraryapi.validator;

import io.github.cursospringboot.libraryapi.exceptions.RegistroDuplicadoException;
import io.github.cursospringboot.libraryapi.model.Autor;
import io.github.cursospringboot.libraryapi.repository.AutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AutorValidator {

    private final AutorRepository autorRepository;

    public void validar(Autor autor){
        if (existeAutoCadastrado(autor)){
            throw new RegistroDuplicadoException("Já existe este autor na base de dados");
        }
    }

    public boolean existeAutoCadastrado(Autor autor){
        Optional<Autor> constaDatabase = autorRepository.findByNomeAndDataNascimentoAndNacionalidade(autor.getNome(), autor.getDataNascimento(), autor.getNacionalidade());
        if (autor.getId() == null){
            return constaDatabase.isPresent();
        }

        return !autor.getId().equals(constaDatabase.get().getId()) && constaDatabase.isPresent();
    }
}
