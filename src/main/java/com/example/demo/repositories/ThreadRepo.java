package com.example.demo.repositories;

import com.example.demo.entities.Thread;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ThreadRepo extends JpaRepository<Thread, Long> {

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM threads WHERE user_id = ?1", nativeQuery = true)
    void deleteUserInThreadTable(long id);
}





