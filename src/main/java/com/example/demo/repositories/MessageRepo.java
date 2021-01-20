package com.example.demo.repositories;

import com.example.demo.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepo extends JpaRepository<Message, Long> {

/*
    List<Message> findAllByThreadId(Long thread_id);

    List<Message> findAllByUserId(Long user_id);

 */
}