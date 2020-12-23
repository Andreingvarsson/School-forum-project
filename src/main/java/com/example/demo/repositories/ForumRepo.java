package com.example.demo.repositories;

import com.example.demo.entities.Forum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForumRepo extends JpaRepository<Forum, Long> {
}
