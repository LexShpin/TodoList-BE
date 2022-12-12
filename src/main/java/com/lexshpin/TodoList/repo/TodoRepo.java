package com.lexshpin.TodoList.repo;

import com.lexshpin.TodoList.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepo extends JpaRepository<Todo, Integer> {

    List<Todo> getAllByUsername(String username);
}
