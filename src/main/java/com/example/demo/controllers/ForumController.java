package com.example.demo.controllers;

import com.example.demo.entities.Forum;
import com.example.demo.services.ForumService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Required role:: Open to all")
    @GetMapping("/forums")
    public ResponseEntity<List<Forum>> getAllForums() {
        var forums = forumService.findAllForums();
        return ResponseEntity.ok(forums);
    }

    @Operation(summary = "Required role:: Open to all")
    @GetMapping("/forums/{id}")
    public ResponseEntity<Forum> getForumById(@PathVariable Long id) {
        var forum = forumService.findById(id);
        return ResponseEntity.ok(forum);
    }

    @Operation(summary = "Required role:: ADMIN")
    @PutMapping("/forums/{forum_id}/moderator/{user_id}")
    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addModeratorToForum(@PathVariable long forum_id, @PathVariable long user_id) {
        forumService.addModerator(forum_id, user_id);
    }

    @Operation(summary = "Required role:: ADMIN")
    @DeleteMapping("/forums/{forum_id}/moderator/{user_id}")
    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeModeratorFromForum(@PathVariable long forum_id, @PathVariable long user_id) {
        forumService.removeModerator(forum_id, user_id);
    }

}
