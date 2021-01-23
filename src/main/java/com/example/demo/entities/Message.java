package com.example.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "messages")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long message_id;
    private long timestamp;
    private String message;
    private Long thread_id;
    private boolean warningMessage;

    public Message(String message, boolean warningMessage, Long thread_id, User user) {
        this.message = message;
        this.thread_id = thread_id;
        this.timestamp = new Date().getTime();
        this.messageOwner = user;
        this.warningMessage = warningMessage;
    }



    @ManyToOne
    @JoinColumn(name = "user_id")
    private User messageOwner;





}