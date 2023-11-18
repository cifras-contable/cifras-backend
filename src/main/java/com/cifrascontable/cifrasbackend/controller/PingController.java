package com.cifrascontable.cifrasbackend.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class PingController {

    @GetMapping("/ping")
    public ResponseEntity<String> pingController() {
        return new ResponseEntity<>("pong", HttpStatus.OK);
    }

}
