package com.example.demo.controllers;


import com.example.demo.entities.Message;
import com.example.demo.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/messages")
public class MessageController {


    /*
    @Autowired
    MessageService messageService;


    @GetMapping
    public ResponseEntity<List<Message>> getAllMessages(@RequestParam(required = false) String search){
        var messages = messageService.getAllMessages(search);
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable Long id){
        var message = messageService.getMessageById(id);
        return ResponseEntity.ok(message);
    }

     */




}
