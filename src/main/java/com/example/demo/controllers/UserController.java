package com.example.demo.controllers;

import com.example.demo.dtos.UserDto;
import com.example.demo.entities.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    UserService userService;

    @Secured("ROLE_ADMIN")
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        var users = userService.getAllUsers();
        System.out.println("Hämtar alla users");
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<User> registerUser(@RequestBody @Validated UserDto userToBeCreated){
        var newUser = userService.registerUser(userToBeCreated);
        var uri = URI.create("/api/v1/users" + newUser.getUser_id());
        return ResponseEntity.created(uri).body(newUser);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody User user){
        userService.update(id, user);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.delete(id);

    }
}


