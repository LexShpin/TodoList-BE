package com.lexshpin.TodoList.service;

import com.lexshpin.TodoList.model.Person;
import com.lexshpin.TodoList.repo.PersonRepo;
import com.lexshpin.TodoList.security.PersonDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService implements UserDetailsService {

    private final PersonRepo personRepo;

    public PersonService(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> person = personRepo.findByUsername(username);

        if (person.isEmpty()) {
            throw new UsernameNotFoundException("Could not find a user with that username");
        }

        return new PersonDetails(person.get());
    }
}
