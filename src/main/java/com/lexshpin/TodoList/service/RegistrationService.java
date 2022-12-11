package com.lexshpin.TodoList.service;

import com.lexshpin.TodoList.model.Person;
import com.lexshpin.TodoList.repo.PersonRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final PersonRepo personRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(PersonRepo personRepo, PasswordEncoder passwordEncoder) {
        this.personRepo = personRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));

        personRepo.save(person);
    }
}
