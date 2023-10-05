package com.wilddev.geo.controllers;

import org.springframework.http.*;

public abstract class AbstractController {

    public ResponseEntity<?> notFound() {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public <T> ResponseEntity<?> ok(T body) {
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
