package com.example.flowauth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @CrossOrigin
    @GetMapping("/communication")
    public ResponseEntity<String> testCommunication(){
        return ResponseEntity.ok("good healthy");
    }

}
