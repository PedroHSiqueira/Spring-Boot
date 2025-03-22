package io.github.cursospringboot.libraryapi.controller;

import io.github.cursospringboot.libraryapi.controller.dto.AutorDTO;
import io.github.cursospringboot.libraryapi.controller.dto.ErroCampo;
import io.github.cursospringboot.libraryapi.controller.dto.ErroResposta;
import io.github.cursospringboot.libraryapi.exceptions.OperacaoNaoPermitidaException;
import io.github.cursospringboot.libraryapi.exceptions.RegistroDuplicadoException;
import io.github.cursospringboot.libraryapi.model.Autor;
import io.github.cursospringboot.libraryapi.service.AutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/autores")
@RequiredArgsConstructor
public class AutorController {

    private final AutorService autorService;

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody AutorDTO autor) {
        try {
            Autor autorEntity = autor.mapearAutor();
            autorService.salvar(autorEntity);

            // Cria a URL = http://localhost:8080/autores/{Id do autor (UUID)}
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(autorEntity.getId())
                    .toUri();

            return ResponseEntity.created(location).build();
        } catch (RegistroDuplicadoException e) {
            ErroResposta erroDto = ErroResposta.conflito(e.getMessage());
            return ResponseEntity.status(erroDto.status()).body(erroDto);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<AutorDTO> obterDetalhes(@PathVariable("id") String id) {
        UUID idAutor = UUID.fromString(id);
        Optional<Autor> buscaAutor = autorService.obterPorId(idAutor);
        if (buscaAutor.isPresent()) {
            Autor autor = buscaAutor.get();
            AutorDTO dto = new AutorDTO(autor.getId(), autor.getNome(), autor.getDataNascimento(), autor.getNacionalidade());
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<AutorDTO>> pesquisar(@RequestParam(value = "nome", required = false) String nome,@RequestParam(value = "nacionalidade", required = false) String nacionalidade){
      List<Autor> resultado = autorService.pesquisa(nome, nacionalidade);
      List<AutorDTO> lista = resultado.stream().map(autor -> new AutorDTO(autor.getId(), autor.getNome(), autor.getDataNascimento(), autor.getNacionalidade())).toList();
      return ResponseEntity.ok(lista);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> atualizar(@PathVariable("id") String id, @RequestBody AutorDTO autorDTO){
        try {
            UUID uuid = UUID.fromString(id);
            Optional<Autor> autorOptional = autorService.obterPorId(uuid);

            if (autorOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            Autor autor = autorOptional.get();
            autor.setNome(autorDTO.nome());
            autor.setDataNascimento(autorDTO.dataNascimento());
            autor.setNacionalidade(autorDTO.nacionalidade());

            autorService.atualizar(autor);
            return ResponseEntity.noContent().build();
        } catch (RegistroDuplicadoException e) {
            ErroResposta erroDto = ErroResposta.conflito(e.getMessage());
            return ResponseEntity.status(erroDto.status()).body(erroDto);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> exclusao(@PathVariable("id") String id) {
        try {

        UUID idAutor = UUID.fromString(id);
        Optional<Autor> autorOptional = autorService.obterPorId(idAutor);

        if (autorOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        autorService.deletar(autorOptional.get());
        return ResponseEntity.noContent().build();
        }catch (OperacaoNaoPermitidaException e){
            ErroResposta erroDto = ErroResposta.respostaPadrao(e.getMessage());
            return ResponseEntity.status(erroDto.status()).body(erroDto);
        }
    }

}
