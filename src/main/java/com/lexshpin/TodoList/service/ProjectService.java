package com.lexshpin.TodoList.service;

import com.lexshpin.TodoList.model.Project;
import com.lexshpin.TodoList.repo.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepo projectRepo;

    @Autowired
    public ProjectService(ProjectRepo projectRepo) {
        this.projectRepo = projectRepo;
    }

    public List<Project> getAllByUsername(String username) {
        return projectRepo.getAllByUsername(username);
    }

    public void update(int id, Project project) {
        project.setId(id);

        projectRepo.save(project);
    }
}
