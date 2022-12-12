package com.lexshpin.TodoList.repo;

import com.lexshpin.TodoList.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepo extends JpaRepository<Project, Integer> {

    List<Project> getAllByUsername(String username);
}
