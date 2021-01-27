package com.example.demo.services;

import com.example.demo.dtos.MessageCreateDto;
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

    @Autowired
    MyUserDetailsService myUserDetailsService;

    // dont use these 2 below so might delete! ***********
    public List<Message> findAllMessages() {
        return messageRepo.findAll();
    }

    public Message getMessageById(Long id) {
        return messageRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find the forum.."));
    }

    /*
    public List<Message> getAllMessagesByThreadId(Long thread_id){
        if(threadRepo.existsById(thread_id)){
            return messageRepo.findAllByThreadId(thread_id);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "A thread with that id doesnt exist.");
    }

    public List<Message> getAllMessagesByUserId(Long user_id){
        if(userRepo.existsById(user_id)){
            return messageRepo.findAllByUserId(user_id);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "a user with that id doesnt exist.");
    }

     */

    public Message createMessage(MessageCreateDto message, Long id) {
        var username = myUserDetailsService.getCurrentUser();
        var user = userRepo.findByUsername(username);

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find the user...");
        }

        var thread = threadRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find the thread..."));
        if (thread.isLockedThread()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The thread is locked...");
        }
        var newMessage = new Message(message.getCreatedMessage(), id, user);

        if (user.getRoles().contains("ADMIN") || user.getModeratedForums().contains(thread.getForum_id())) {
            newMessage.setWarningMessage(message.isWarningMessage());
        } else {
            newMessage.setWarningMessage(false);
        }
        return messageRepo.save(newMessage);
    }


/*
    public void update(Long id, Message message) {
        if(!messageRepo.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find the message with that id..");
        }
        message.setMessage_id(id);
        messageRepo.save(message);
    }
 */


    public void delete(Long id) {

        var message = messageRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "could not find message..."));

        var username = myUserDetailsService.getCurrentUser();
        var user = userRepo.findByUsername(username);

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find the user...");
        }
        var thread = threadRepo.findById(message.getThread_id()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find the thread..."));

        if (user.getRoles().contains("ADMIN") || user.getModeratedForums().contains(thread.getForum_id())) {
            System.out.println("INNE I SKITEN");
            messageRepo.deleteById(message.getMessage_id());
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "not authorized to delete message...");
        }
    }
}





