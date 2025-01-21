package com.arquitetura.arquiteturaspring.Todos;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/todos")
public class TodoController {
    private TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public TodoEntity buscar(@PathVariable("id") Integer id){
        return this.service.buscaPorId(id);
    }

    @PostMapping
    public TodoEntity salvar(@RequestBody TodoEntity todo){
        try{
            return this.service.salvar(todo);
        }catch (IllegalArgumentException e){
            String mensagem = e.getMessage();
            throw new ResponseStatusException(HttpStatus.CONFLICT, mensagem);
        }
    }

    @PutMapping("{id}")
    public void atualizarStatus(@PathVariable("id") Integer id, @RequestBody TodoEntity todo){
        todo.setId(id);
        this.service.atualizarStatus(todo);
    }
}
