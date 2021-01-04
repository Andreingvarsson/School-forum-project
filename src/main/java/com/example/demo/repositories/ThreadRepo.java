package com.example.demo.repositories;

import com.example.demo.entities.Thread;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThreadRepo extends JpaRepository<Thread, Long> {
}





