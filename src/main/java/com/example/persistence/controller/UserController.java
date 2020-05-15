package com.example.persistence.controller;

import com.example.persistence.model.User;
import com.example.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/demo")
public class UserController {

    @Autowired
    UserRepository repository;

    @PostMapping("/add")
    public @ResponseBody
    String addNewUser(@RequestParam String firstName,
                      @RequestParam String lastName) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        repository.save(user);
        return "Success";
    }

    @GetMapping("/all")
    public @ResponseBody
    Iterable<User> getAllUsers() {
        return repository.findAll();
    }
}