package com.example.demo.controllers;

import com.example.demo.entities.Forum;
import com.example.demo.services.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ForumController {

    @Autowired
    ForumService forumService;


    @GetMapping("/forums")
   public ResponseEntity<List<Forum>> getAllForums(){
        var forums = forumService.findAllForums();
        System.out.println("Hämtar alla forums");
        return ResponseEntity.ok(forums);
    }

    @GetMapping("/forums/{id}")
    public ResponseEntity<Forum> getForumById(@PathVariable Long id){
        var forum = forumService.findById(id);
        return ResponseEntity.ok(forum);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Forum> addForum(@RequestBody Forum forum){
        var newForum = forumService.createForum(forum);
        var uri = URI.create("/api/v1/forum/" + newForum.getForum_id());
        return ResponseEntity.created(uri).body(newForum);
    }

    @PutMapping("/forums/{forum_id}/moderator/{user_id}")
    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addModeratorToForum(@PathVariable long forum_id, @PathVariable long user_id){
        forumService.addModerator(forum_id, user_id);
    }

    @DeleteMapping("/forums/{forum_id}/moderator/{user_id}")
    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeModeratorFromForum(@PathVariable long forum_id, @PathVariable long user_id){
        forumService.removeModerator(forum_id, user_id);
    }

    /*
    @Secured("ROLE_ADMIN")
    @PutMapping("/forums/{id}")
    public void updateForum(@PathVariable Long id, @RequestBody Forum forum){
        forumService.update(id, forum);
    }

     */

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/forums/{id}")
    public void deleteForum(@PathVariable Long id){
        forumService.delete(id);
    }





}
