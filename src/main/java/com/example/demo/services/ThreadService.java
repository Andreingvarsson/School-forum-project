package com.example.demo.services;

import com.example.demo.repositories.ThreadRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThreadService {

    @Autowired
    ThreadRepo threadRepo;
}
