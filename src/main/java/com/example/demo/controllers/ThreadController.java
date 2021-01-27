package com.example.demo.controllers;

import com.example.demo.dtos.ThreadCreateDto;
import com.example.demo.entities.Thread;
import com.example.demo.services.ThreadService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
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


    // be able to Use this one?!?! *******
    @Operation(summary = "Required role:: Open to all")
    @GetMapping("/threads")
    public ResponseEntity<List<Thread>> getAllThreads() {
        var threads = threadService.findAllThreads();
        return ResponseEntity.ok(threads);
    }

    @Operation(summary = "Required role:: Open to all")
    @GetMapping("/threads/{id}")
    public ResponseEntity<Thread> getThreadById(@PathVariable Long id) {
        var thread = threadService.findById(id);
        return ResponseEntity.ok(thread);
    }


    @Operation(summary = "Required role:: USER, ADMIN, MODERATOR")
    @PostMapping("/forums/{id}/threads")
    @Secured({"ROLE_USER", "ROLE_ADMIN", "ROLE_MODERATOR"})
    public ResponseEntity<Thread> addThread(@RequestBody ThreadCreateDto thread, @PathVariable long id) {
        var newThread = threadService.createThread(thread, id);
        var uri = URI.create("/api/v1/thread" + newThread.getThread_id());
        return ResponseEntity.created(uri).body(newThread);
    }

    @Operation(summary = "Required role:: ADMIN, MODERATOR")
    @PutMapping("/forums/{id}/threads")
    @Secured({"ROLE_ADMIN", "ROLE_MODERATOR"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateThread(@PathVariable Long id) {
        threadService.update(id);
    }


    @Operation(summary = "Required role:: ADMIN")
    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteThread(@PathVariable Long id) {
        threadService.delete(id);
    }
}




