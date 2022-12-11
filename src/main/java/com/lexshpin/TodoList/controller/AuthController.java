package com.lexshpin.TodoList.controller;

import com.lexshpin.TodoList.dto.PersonDTO;
import com.lexshpin.TodoList.model.Person;
import com.lexshpin.TodoList.service.PersonService;
import com.lexshpin.TodoList.service.RegistrationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {

    private final RegistrationService registrationService;
    private final AuthenticationManager authenticationManager;
    private final PersonService personService;
    private final ModelMapper modelMapper;

    @Autowired
    public AuthController(RegistrationService registrationService, AuthenticationManager authenticationManager, PersonService personService, ModelMapper modelMapper) {
        this.registrationService = registrationService;
        this.authenticationManager = authenticationManager;
        this.personService = personService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/register")
    public ResponseEntity<PersonDTO> registerPerson(@RequestBody PersonDTO personDTO) {
        Person person = convertToPerson(personDTO);

        registrationService.register(person);

        return new ResponseEntity(person, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<PersonDTO> login(@RequestBody PersonDTO personDTO) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(personDTO.getUsername(), personDTO.getPassword());
    }

    private Person convertToPerson(PersonDTO personDTO) {

        return modelMapper.map(personDTO, Person.class);
    }
}
