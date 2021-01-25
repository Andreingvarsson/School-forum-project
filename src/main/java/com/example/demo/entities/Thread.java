package com.example.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@Entity
@Table(name = "threads")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Thread {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long thread_id;
    private String title;
    private String threadMessage;
    private Long timestamp;
    private Long forum_id;
    private boolean lockedThread;


    public Thread(String title, String threadMessage, Long forum_id, User user){
        this.title = title;
        this.threadMessage = threadMessage;
        this.forum_id = forum_id;
        this.timestamp = new Date().getTime();
        this.threadOwner = user;
        this.lockedThread = false;

    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("timestamp ASC")
    @JoinColumn(name = "thread_id")
    private Set<Message> messages;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User threadOwner;



}