package com.example.persistence.controller;

import com.example.persistence.model.User;
import com.example.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/demo")
public class UserController {

    @Autowired
    UserRepository repository;

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<User> addNewUser(@RequestBody User user) {
        return new ResponseEntity<>(repository.save(user), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<List<User>>((List<User>) repository.findAll(), HttpStatus.OK);
    }
}