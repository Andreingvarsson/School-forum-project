package com.example.demo.controllers;


import com.example.demo.services.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/threads")
public class ThreadController {

    @Autowired
    ThreadService threadService;
}
