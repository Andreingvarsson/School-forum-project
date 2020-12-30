/*

package com.example.demo.services;

import com.example.demo.entities.Message;
import com.example.demo.repositories.MessageRepo;
import com.example.demo.repositories.ThreadRepo;
import com.example.demo.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    MessageRepo messageRepo;

    @Autowired
    ThreadRepo threadRepo;

    @Autowired
    UserRepo userRepo;

    public List<Message> getAllMessagesByThreadId(Long threadId){
        if(threadRepo.existsById(threadId)){
            return messageRepo.findAllByThreadId(threadId);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "A thread with that id doesnt exist.");
    }

    public List<Message> getAllMessagesByUserId(Long userId){
        if(userRepo.existsById(userId)){
            return messageRepo.findAllByUserId(userId);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "a user with that id doesnt exist.");
    }

}

 */
