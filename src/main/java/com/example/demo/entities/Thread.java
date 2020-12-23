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
    private String topic;
    private Long timestamp = new Date().getTime();
    private Long user_id;


   /* @OneToMany
    @JoinColumn(name = "thread_id")
    private Set<Message> messages;

    */
}