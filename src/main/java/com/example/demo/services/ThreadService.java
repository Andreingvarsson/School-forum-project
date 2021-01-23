package com.example.demo.services;

import com.example.demo.dtos.ThreadCreateDto;
import com.example.demo.entities.Thread;
import com.example.demo.entities.User;
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
      //if(!threadRepo.existsById(id)){
      //    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find the thread with that id..");
     // }
      var thread = threadRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find the thread.."));
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


