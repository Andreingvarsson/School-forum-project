
package com.example.demo.services;

import com.example.demo.entities.Forum;
import com.example.demo.repositories.ForumRepo;
import com.example.demo.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class ForumService {

    @Autowired
    ForumRepo forumRepo;

    @Autowired
    UserRepo userRepo;


    public List<Forum> findAllForums() {
        return forumRepo.findAll();
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
        forum.setForum_id(id);
        forumRepo.save(forum);
    }

    public void delete(Long id) {
        if(!forumRepo.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find the forum with that id..");
        }
        forumRepo.deleteById(id);
    }

    public void addModerator(long forum_id, long user_id){
        var forum = forumRepo.findById(forum_id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Can't find forum with that id.."));
        var user = userRepo.findById(user_id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Can't find the user with that id..."));

        // ... what to add, need user and forum to decide where and who...
        if(forum.getModerators().contains(user)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already moderate forum..");
        }
        if(!user.getRoles().contains("MODERATOR")){
            user.setRoles(user.getRoles() + ",MODERATOR");
        }
        forum.getModerators().add(user);
        forumRepo.save(forum);
    }

    public void removeModerator(long forum_id, long user_id){
        var forum = forumRepo.findById(forum_id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Can't find forum with that id.."));
        var user = userRepo.findById(user_id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Can't find the user with that id..."));

        // ... what to add, need user and forum to decide where and who...
        if(!user.getModeratedForums().contains(forum.getForum_id())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User doesn't moderate forum..");
        }
        user.removeForum(forum);
        if(user.getModeratedForums().size() == 0){
            user.setRoles(user.getRoles().replace(",MODERATOR", ""));
        }
        userRepo.save(user);
    }


}


