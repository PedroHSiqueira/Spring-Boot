package com.arquitetura.arquiteturaspring.Todos;

import org.springframework.stereotype.Service;

@Service
public class TodoService {

    private final TodoRepository repository;

    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    public TodoEntity salvar(TodoEntity novoTodo){
        return repository.save(novoTodo);
    }

    public void atualizarStatus(TodoEntity todo){
        repository.save(todo);
    }

    public TodoEntity buscaPorId(Integer id){
        return this.repository.findById(id).orElse(null);
    }
}
