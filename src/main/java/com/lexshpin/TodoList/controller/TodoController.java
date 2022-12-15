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
@RequestMapping("/todos")
@CrossOrigin
public class TodoController {

    private final TodoService todoService;
    private final TodoRepo todoRepo;

    @Autowired
    public TodoController(TodoService todoService, TodoRepo todoRepo) {
        this.todoService = todoService;
        this.todoRepo = todoRepo;
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<Todo>> getAllPersonsTodos(@PathVariable("username") String username) {

        List<Todo> personsTodos = todoService.getAllByUsername(username);

        return new ResponseEntity<>(personsTodos, HttpStatus.OK);
    }

    @GetMapping("/{username}/{projectId}")
    public ResponseEntity<List<Todo>> getAllPersonsTodosByProjectId(@PathVariable("username") String username, @PathVariable("projectId") int projectId) {

        List<Todo> personsTodosByProjectId = todoService.getAllByProjectId(projectId);

        return new ResponseEntity<>(personsTodosByProjectId, HttpStatus.OK);
    }

    @GetMapping("/{username}/today")
    public ResponseEntity<List<Todo>> getAllPersonsTodosForToday(@PathVariable("username") String username) {

    }

    @GetMapping("/{username}/upcoming")
    public ResponseEntity<List<Todo>> getAllUpcomingPersonsTodos(@PathVariable("username") String username) {

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
