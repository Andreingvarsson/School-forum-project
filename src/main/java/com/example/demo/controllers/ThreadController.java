package com.example.demo.controllers;

import com.example.demo.services.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/threads")
public class ThreadController {

    @Autowired
    ThreadService threadService;

    @GetMapping
    public ResponseEntity<List<Thread>> getAllThreads(){
        var threads = threadService.findAllThreads();
        return ResponseEntity.ok(threads);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Thread> getThreadById(@PathVariable Long id){
        var thread = threadService.findById(id);
        return ResponseEntity.ok(thread);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Thread> addThread(@RequestBody Thread thread){
        var newThread = threadService.createThread(thread);
        var uri = URI.create("/api/v1/thread" + newThread.getId());  // getThread_id fungerar ej?!
        return ResponseEntity.created(uri).body(newThread);
    }

    @PutMapping("/{id}")
    public void updateThread(@PathVariable Long id, @RequestBody Thread thread){
        threadService.update(id, thread);
    }

    @DeleteMapping("/{id}")
    public void deleteThread(@PathVariable Long id){
        threadService.delete(id);
    }
}


