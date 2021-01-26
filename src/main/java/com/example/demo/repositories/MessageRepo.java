package com.example.demo.repositories;

import com.example.demo.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface MessageRepo extends JpaRepository<Message, Long> {

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM messages WHERE user_id = ?1", nativeQuery = true)
    void deleteUserInMessagesTable(long id);
}