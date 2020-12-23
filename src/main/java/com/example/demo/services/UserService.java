package com.example.demo.services;

import com.example.demo.dtos.UserDto;
import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    MyUserDetailService myUserDetailService;

    public User getCurrentUser(){
        String username = myUserDetailService.getCurrentUser();
        return userRepo.findByUsername(username);
    }

    public User findByUsername(String username){return userRepo.findByUsername(username);}

    public List<User> getAllUsers(){return userRepo.findAll();}

    public User registerUser(UserDto userToCreate){
        if(userRepo.existsByUsername(userToCreate.getUsername())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists.");
        }
        var newUser = myUserDetailService.addUser(userToCreate.getUsername(), userToCreate.getEmail(), userToCreate.getPassword(), userToCreate.getRoles());
        return userRepo.save(newUser);
    }
}
