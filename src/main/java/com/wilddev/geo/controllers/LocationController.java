package com.wilddev.geo.controllers;

import com.wilddev.geo.controllers.responses.LocationResponse;
import fun.wilddev.spring.web.controllers.AbstractController;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;

import com.wilddev.geo.services.*;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/location")
@RestController
@AllArgsConstructor
public class LocationController extends AbstractController {

    private final LocationCache locationCache;

    @GetMapping
    public ResponseEntity<?> detect(@RequestHeader("X-Real-IP") String ip) {
        return locationCache.detect(ip).map(LocationResponse::new)
                .map(this::ok).orElse(notFound());
    }

    @GetMapping("/list")
    public ResponseEntity<?> list(@RequestParam("q") String qualifier) {
        return ok(locationCache.list(qualifier).stream().map(LocationResponse::new).toList());
    }
}
