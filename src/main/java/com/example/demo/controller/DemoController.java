package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1")
@RestController
public class DemoController {

    @GetMapping("/greet")
    public ResponseEntity<String> greet(){
        return ResponseEntity.ok("hello");
    }

}
