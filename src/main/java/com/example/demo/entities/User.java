package com.example.demo.entities;

import com.example.demo.dtos.UserDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long user_id;
    private String username;
    private String email;
    private String password;
    private String roles;

    public User(UserDto user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.roles = user.getRoles();
    }

    public User(String username, String email, String password, String roles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.moderatedForums = Set.of();
    }

    @ManyToMany(mappedBy = "moderators", fetch = FetchType.LAZY)
    private Set<Forum> moderatedForums;

    public List<Long> getModeratedForums() {
        return moderatedForums.stream().map(forum -> forum.getForum_id()).collect(Collectors.toList());
    }

    public void removeForum(Forum forum) {
        this.moderatedForums.remove(forum);
        forum.getModerators().remove(this);
    }

    // Constructor for anonymous user.
    public User(String username) {
        this.username = username;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

}
