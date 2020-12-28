package com.example.demo.services;

import com.example.demo.entities.Forum;
import com.example.demo.repositories.ForumRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class ForumService {

    @Autowired
    ForumRepo forumRepo;


    public void findAll(String name) {
    }

    public Forum findById(Long id) {
        return forumRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find the forum.."));
    }

    public Forum createForum(Forum forum) {
        return forumRepo.save(forum);
    }

    public void update(Long id, Forum forum) {
        if(!forumRepo.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find the forum with that id..");
        }
        forum.setId(id);
        forumRepo.save(forum);
    }

    public void delete(Long id) {
        if(!forumRepo.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find the forum with that id..");
        }
        forumRepo.deleteById(id);
    }



}
