package io.github.cursospringboot.libraryapi.service;

import io.github.cursospringboot.libraryapi.exceptions.OperacaoNaoPermitidaException;
import io.github.cursospringboot.libraryapi.model.Autor;
import io.github.cursospringboot.libraryapi.repository.AutorRepository;
import io.github.cursospringboot.libraryapi.repository.LivroRepository;
import io.github.cursospringboot.libraryapi.validator.AutorValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AutorService {

    private final AutorRepository autorRepository;
    private final AutorValidator autorValidator;
    private final LivroRepository livroRepository;

    public Autor salvar(Autor autor) {
        autorValidator.validar(autor);
        return autorRepository.save(autor);
    }

    public void atualizar(Autor autor) {
        if (autor.getId() == null){
            throw new IllegalArgumentException("O autor precisa estar cadastrado antes da atualização!");
        }
        autorValidator.validar(autor);
        autorRepository.save(autor);
    }

    public Optional<Autor> obterPorId(UUID id) {
        return autorRepository.findById(id);
    }

    public List<Autor> pesquisa(String nome, String nacionalidade) {
        if (nome != null && nacionalidade != null) {
            return autorRepository.findByNomeAndNacionalidade(nome, nacionalidade);
        }

        if (nome != null) {
            return autorRepository.findByNomeContaining(nome);
        }

        if (nacionalidade != null) {
            return autorRepository.findByNacionalidade(nacionalidade);
        }

        return autorRepository.findAll();
    }

    public void deletar(Autor autor) {
        if (possuiLivros(autor)){
            throw new OperacaoNaoPermitidaException("O autor possui livros cadastrados, por isso não pode ser excluido!");
        }
        autorRepository.delete(autor);
    }

    public boolean possuiLivros(Autor autor){
        return livroRepository.existsByAutor(autor);
    }
}
