package com.arquitetura.arquiteturaspring.Todos;

import org.springframework.stereotype.Component;

@Component
public class TodoValidator {
    private TodoRepository repository;

    public TodoValidator(TodoRepository repository) {
        this.repository = repository;
    }

    public void validar(TodoEntity todo){
        if (existeDescricao(todo.getDescricao())){
            throw new IllegalArgumentException("Já existe um TODO com esta descrição");
        }
    }

    public boolean existeDescricao(String descricao){
        return repository.existsByDescricao(descricao);
    }
}
