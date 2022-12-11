package com.lexshpin.TodoList.repo;

import com.lexshpin.TodoList.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepo extends JpaRepository<Person, Integer> {

    Optional<Person> findByUsername(String username);
}
