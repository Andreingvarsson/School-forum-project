package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "forums")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Forum {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long forum_id;
    private String name;

    @OneToMany
    @OrderBy("timestamp ASC")
    @JoinColumn(name = "forum_id")
    private Set<Thread> threads;

    @ManyToMany
    @JoinTable(name = "moderators", joinColumns = @JoinColumn(name = "forum_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> moderators;


}