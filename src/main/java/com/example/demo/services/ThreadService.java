package com.example.demo.services;

import com.example.demo.dtos.ThreadCreateDto;
import com.example.demo.entities.Thread;
import com.example.demo.repositories.ForumRepo;
import com.example.demo.repositories.ThreadRepo;
import com.example.demo.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;

@Service
public class ThreadService {

    @Autowired
    ThreadRepo threadRepo;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Autowired
    UserRepo userRepo;

    @Autowired
    ForumRepo forumRepo;

  public List<Thread> findAllThreads(){
      return threadRepo.findAll();
  }

  public Thread findById(Long id){
      return threadRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find the thread.."));
  }

  public Thread createThread(ThreadCreateDto thread, Long id){
      var username = myUserDetailsService.getCurrentUser();
      var user = userRepo.findByUsername(username);
      if(user == null){
          throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find the user...");
      }
      var newThread = new Thread(thread.getThreadTitle(),thread.getThreadMessage(), id, user);
      return threadRepo.save(newThread);
  }

  public void update(Long id){

      var username = myUserDetailsService.getCurrentUser();
      var user = userRepo.findByUsername(username);

      var thread = threadRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find the thread.."));
      if(!user.getRoles().contains("ADMIN")){
          var forum = forumRepo.findById(thread.getForum_id()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "could not find forum of that thread.."));
          if(!user.getModeratedForums().contains(forum.getForum_id())){
              throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Not authorized..");
          }
      }
      thread.setLockedThread(!thread.isLockedThread());
      threadRepo.save(thread);
  }

  public void delete(Long id){
      if(!threadRepo.existsById(id)){
          throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find the thread with that id..");
      }
      threadRepo.deleteById(id);
  }

}


