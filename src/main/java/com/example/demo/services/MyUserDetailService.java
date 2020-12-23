package com.example.demo.services;

import com.example.demo.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Configuration
public class MyUserDetailService implements UserDetailsService {

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    UserRepo userRepo;

    @Autowired
    UserService userService;

    @PostConstruct
    private void createDefaultUsers() {
        if (userRepo.findByUsername("test") == null) {
            addUser("test", "test@test.com", "test", "USER");
        }
    }

    public User addUser(String username, String email, String password, String roles){
        User user = new User(username, email, encoder.encode(password), roles);
        try{
            return userRepo.save(user);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public String getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    private Collection<GrantedAuthority> getGrantedAuthorities(User user) {
        return Arrays.stream(user.getRoles().split(","))
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toSet());
    }


    public BCryptPasswordEncoder getEncoder() {return encoder;}
}
