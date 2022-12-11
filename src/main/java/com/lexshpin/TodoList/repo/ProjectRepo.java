package com.lexshpin.TodoList.repo;

import com.lexshpin.TodoList.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepo extends JpaRepository<Project, Integer> {
}
