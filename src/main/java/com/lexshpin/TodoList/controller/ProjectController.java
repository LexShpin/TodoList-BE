package com.lexshpin.TodoList.controller;

import com.lexshpin.TodoList.model.Project;
import com.lexshpin.TodoList.model.Todo;
import com.lexshpin.TodoList.repo.ProjectRepo;
import com.lexshpin.TodoList.service.ProjectService;
import com.lexshpin.TodoList.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectRepo projectRepo;
    private final ProjectService projectService;
    private final TodoService todoService;

    @Autowired
    public ProjectController(ProjectRepo projectRepo, ProjectService projectService, TodoService todoService) {
        this.projectRepo = projectRepo;
        this.projectService = projectService;
        this.todoService = todoService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<Project>> getAllPersonProjects(@PathVariable("username") String username) throws Exception {

        try {
            List<Project> personProjects = projectService.getAllByUsername(username);
            return new ResponseEntity<>(personProjects, HttpStatus.OK);
        } catch (Exception e) {
            throw new Exception("Could not load user's list of projects");
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Todo>> getProjectTodos(@PathVariable("id") int id) throws Exception {

        //TODO: Load all project todos here
        try {
            List<Todo> projectTodos = todoService.getAllByProjectId(id);
            return new ResponseEntity<>(projectTodos, HttpStatus.OK);
        } catch (Exception e) {
            throw new Exception("Could not load this project's todos");
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Project> addProject(@RequestBody Project project) throws Exception {

        try {
            projectRepo.save(project);
            return new ResponseEntity<>(project, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new Exception("Could not add new project");
        }
    }

    @PostMapping("/{id}/edit")
    public ResponseEntity<Project> editProject(@PathVariable("id") int id, @RequestBody Project project) throws Exception {

        try {
            projectService.update(id, project);
            return new ResponseEntity<>(project, HttpStatus.OK);
        } catch (Exception e) {
            throw new Exception("Could not update project");
        }
    }

    @PostMapping("/{id}/delete")
    public ResponseEntity<?> deleteProject(@PathVariable("id") int id) throws Exception {

        try {
            projectRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new Exception("Could not delete project");
        }
    }
}
