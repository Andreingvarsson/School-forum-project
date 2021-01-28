package com.example.demo.controllers;

import com.example.demo.dtos.MessageCreateDto;
import com.example.demo.entities.Message;
import com.example.demo.services.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MessageController {

    @Autowired
    MessageService messageService;

    @Operation(summary = "Required role:: USER, ADMIN, MODERATOR")
    @Secured({"ROLE_USER", "ROLE_ADMIN", "ROLE_MODERATOR"})
    @PostMapping("/threads/{thread_id}/messages")
    public ResponseEntity<Message> addMessage(@RequestBody MessageCreateDto message, @PathVariable long thread_id) {
        var newMessage = messageService.createMessage(message, thread_id);
        var uri = URI.create("/api/v1/messages" + newMessage.getMessage_id());
        return ResponseEntity.created(uri).body(newMessage);
    }

    @Operation(summary = "Required role:: ADMIN, MODERATOR")
    @Secured({"ROLE_ADMIN", "ROLE_MODERATOR"})
    @DeleteMapping("/message/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMessage(@PathVariable Long id) {
        messageService.deleteAMessage(id);
    }
}




