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
    private Long timestamp; //= new Date().getTime();
    private Long forum_id;

    public Thread(String title, Long forum_id){
        this.title = title;
        this.forum_id = forum_id;
        this.timestamp = new Date().getTime();
    }

    public void setId(Long id){
         this.thread_id = id;
    } // fungerar ej?!

    public Long getThread_Id(){
        return thread_id;
    }

/*
    @OneToMany
    @JoinColumn(name = "thread_id")
    private Set<Message> messages;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User threadOwner;


 */

}