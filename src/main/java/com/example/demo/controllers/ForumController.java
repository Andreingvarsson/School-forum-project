package com.example.demo.controllers;

import com.example.demo.entities.Forum;
import com.example.demo.services.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/forums")
public class ForumController {

    @Autowired
    ForumService forumService;


    @GetMapping
   public ResponseEntity<List<Forum>> getAllForums(){
        var forums = forumService.findAllForums();
        return ResponseEntity.ok(forums);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Forum> getForumById(@PathVariable Long id){
        var forum = forumService.findById(id);
        return ResponseEntity.ok(forum);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Forum> addForum(@RequestBody Forum forum){
        var newForum = forumService.createForum(forum);
        var uri = URI.create("/api/v1/forum/" + newForum.getforum_Id());
        return ResponseEntity.created(uri).body(newForum);
    }

    @PutMapping("/{id}")
    public void updateForum(@PathVariable Long id, @RequestBody Forum forum){
        forumService.update(id, forum);
    }

    @DeleteMapping("/{id}")
    public void deleteForum(@PathVariable Long id){
        forumService.delete(id);
    }
}
