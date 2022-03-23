package com.valts.ob_forum_demo.controllers;

import com.valts.ob_forum_demo.models.Curso;
import com.valts.ob_forum_demo.models.User;
import com.valts.ob_forum_demo.servicios.UserService;
import com.valts.ob_forum_demo.servicios.implementations.CursoServiceImpl;
import com.valts.ob_forum_demo.servicios.implementations.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/foro/users")
    public ResponseEntity<List<User>> getAll() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/foro/users/{id}")
    public ResponseEntity<User> getOne(@PathVariable Long id) {
        User user = userService.findOne(id);
        return ResponseEntity.ok(user);
    }


    @PostMapping("/foro/users")
    public ResponseEntity<User> addOne(@RequestBody User user) {
        User newUser =  userService.addOne(user);
        return ResponseEntity.ok(newUser);
    }

    @PutMapping("/foro/users/{id}")
    public ResponseEntity<User> updateOne(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = userService.updateOne(id, user);
        return ResponseEntity.ok(updatedUser);
    }


    @DeleteMapping("/foro/users/{id}")
    public ResponseEntity deleteOne(@PathVariable Long id)  {
        userService.deleteOne(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/foro/users")
    public ResponseEntity deleteAll() {
        userService.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
