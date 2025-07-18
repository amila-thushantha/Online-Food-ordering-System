package com.amila.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public ResponseEntity<String> homeController(){
        return new ResponseEntity<>("Welcome to food delivery project", HttpStatus.OK);
    }
}
