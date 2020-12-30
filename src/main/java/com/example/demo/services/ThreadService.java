package com.example.demo.services;

import com.example.demo.repositories.ThreadRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ThreadService {

    @Autowired
    ThreadRepo threadRepo;

    public List<Thread> findAllThreads(){
        return threadRepo.findAll();
    }

    public Thread findById(Long id){
        return threadRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find the thread.."));
    }

    public Thread createThread(Thread thread) {
        return threadRepo.save(thread);
    }

    public void update(Long id, Thread thread){
        if(!threadRepo.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find the thread with that id..");
        }
        thread.setId(id); // varför fungerar de ej?!
        threadRepo.save(thread);
    }

    public void delete(Long id){
        if(!threadRepo.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find the thread with that id..");
        }
        threadRepo.deleteById(id);
    }

}


