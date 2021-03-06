package com.example.demo.controllers;

import com.example.demo.dtos.UserDto;
import com.example.demo.entities.User;
import com.example.demo.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Operation(summary = "Required role:: ADMIN")
    @Secured("ROLE_ADMIN")
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        var users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @Operation(summary = "Required role:: Open to all")
    @PostMapping
    public ResponseEntity<User> registerUser(@RequestBody @Validated UserDto userToBeCreated) {
        var newUser = userService.registerUser(userToBeCreated);
        var uri = URI.create("/api/v1/users" + newUser.getUser_id());
        return ResponseEntity.created(uri).body(newUser);
    }

    @Operation(summary = "Required role:: ADMIN")
    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }

}


