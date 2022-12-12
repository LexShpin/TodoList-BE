package com.lexshpin.TodoList.controller;

import com.lexshpin.TodoList.model.Todo;
import com.lexshpin.TodoList.repo.TodoRepo;
import com.lexshpin.TodoList.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;
    private final TodoRepo todoRepo;

    @Autowired
    public TodoController(TodoService todoService, TodoRepo todoRepo) {
        this.todoService = todoService;
        this.todoRepo = todoRepo;
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<Todo>> getAllPersonTodos(@PathVariable("username") String username) {

        List<Todo> personTodos = todoService.getAllByUsername(username);

        return new ResponseEntity<>(personTodos, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Todo> addTodo(@RequestBody Todo todo) {

        todoRepo.save(todo);

        return new ResponseEntity<>(todo, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/edit")
    public ResponseEntity<Todo> editTodo(@PathVariable("id") int id, @RequestBody Todo todo) throws Exception {

        try {
            todoService.update(id, todo);
            return new ResponseEntity<>(todo, HttpStatus.OK);
        } catch (Exception e) {
            throw new Exception("Could not update this todo");
        }

    }

    @PostMapping("/{id}/delete")
    public ResponseEntity<?> deleteTodo(@PathVariable("id") int id) throws Exception {

        try {
            todoRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new Exception("Could not delete that todo");
        }
    }
}
