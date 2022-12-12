package com.lexshpin.TodoList.service;

import com.lexshpin.TodoList.model.Todo;
import com.lexshpin.TodoList.repo.TodoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService{

    private final TodoRepo todoRepo;

    @Autowired
    public TodoService(TodoRepo todoRepo) {
        this.todoRepo = todoRepo;
    }

    public List<Todo> getAllByUsername(String username) {
        return this.todoRepo.getAllByUsername(username);
    }

    public void update(int id, Todo todo) {
        todo.setId(id);

        todoRepo.save(todo);
    }
}
