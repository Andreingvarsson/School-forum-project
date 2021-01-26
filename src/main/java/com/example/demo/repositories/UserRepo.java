package com.example.demo.repositories;

import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    User findByUsername(String username);
    Boolean existsByUsername(String username);

    /*
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM users WHERE user_id = ?1", nativeQuery = true)
    void deleteUserInUsersTable(long id);

     */
}