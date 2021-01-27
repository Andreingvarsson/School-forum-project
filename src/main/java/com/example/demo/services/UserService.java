package com.example.demo.services;

import com.example.demo.dtos.UserDto;
import com.example.demo.entities.User;
import com.example.demo.repositories.MessageRepo;
import com.example.demo.repositories.ThreadRepo;
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
    ThreadRepo threadRepo;

    @Autowired
    MessageRepo messageRepo;

    @Autowired
    MyUserDetailsService myUserDetailService;

    public User getCurrentUser() {
        String username = myUserDetailService.getCurrentUser();
        return userRepo.findByUsername(username);
    }

    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User registerUser(UserDto userToCreate) {
        if (userRepo.existsByUsername(userToCreate.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists.");
        }
        var newUser = myUserDetailService.addUser(
                userToCreate.getUsername(),
                userToCreate.getEmail(),
                userToCreate.getPassword(),
                userToCreate.getRoles());
        return userRepo.save(newUser);
    }

    public void delete(Long id) {

        if (!userRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find the user with that id..");
        }
        userRepo.deleteById(id);
        threadRepo.deleteUserInThreadTable(id);
        messageRepo.deleteUserInMessagesTable(id);
    }

        /*
    public void update(Long id, User user) {
        if (!userRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find the user with that id..");
        }
        user.setUser_id(id);
        userRepo.save(user);
    }
     */
}


