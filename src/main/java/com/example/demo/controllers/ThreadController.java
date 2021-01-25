package com.example.demo.controllers;

import com.example.demo.dtos.ThreadCreateDto;
import com.example.demo.entities.Thread;
import com.example.demo.services.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ThreadController {

    @Autowired
    ThreadService threadService;

    @GetMapping
    public ResponseEntity<List<Thread>> getAllThreads(){
        var threads = threadService.findAllThreads();
        return ResponseEntity.ok(threads);
    }

    @GetMapping("/threads/{id}")
    public ResponseEntity<Thread> getThreadById(@PathVariable Long id){
        var thread = threadService.findById(id);
        return ResponseEntity.ok(thread);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN", "ROLE_MODERATOR" })
    @PostMapping("/forums/{id}/threads")
    public ResponseEntity<Thread> addThread(@RequestBody ThreadCreateDto thread, @PathVariable long id){
        var newThread = threadService.createThread(thread, id);
        var uri = URI.create("/api/v1/thread" + newThread.getThread_id());
        return ResponseEntity.created(uri).body(newThread);
    }

    @Secured({"ROLE_ADMIN", "ROLE_MODERATOR"})
    @PutMapping("/forums/{id}/threads")
    public void updateThread(@PathVariable Long id){
        threadService.update(id);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public void deleteThread(@PathVariable Long id){
        threadService.delete(id);
    }
}




