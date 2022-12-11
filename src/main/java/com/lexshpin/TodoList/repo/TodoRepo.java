package com.lexshpin.TodoList.repo;

import com.lexshpin.TodoList.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepo extends JpaRepository<Todo, Integer> {
}
