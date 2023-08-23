package com.example.webfluxsandbox.controller;

// rest controller

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    // @GetMapping("/hello")
    // public Mono<String> helloWorld() {
    //     return Mono.just("Hello, World!");
    // }

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello, World!";
    }
}