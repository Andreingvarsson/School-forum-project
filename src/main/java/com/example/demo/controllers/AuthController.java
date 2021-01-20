package com.example.demo.controllers;

import com.example.demo.entities.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserService userService;

    @GetMapping("/whoami")
    public ResponseEntity<User> whoami() {
        User user = userService.getCurrentUser();
    /*if (user == null) {
      User anonymousUser = new User("");
      return ResponseEntity.ok(anonymousUser);
    }*/
        return ResponseEntity.ok(user);
    }

    @GetMapping("/logout")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void logoutUser (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error found while trying to logout");
        }
    }
}
