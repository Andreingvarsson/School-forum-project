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
    private long timestamp = new Date().getTime();
    private String message;
    private Long thread_id;


   /* @ManyToOne
    @JoinColumn(name = "user_id")
    */




}