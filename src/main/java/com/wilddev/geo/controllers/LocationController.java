package com.wilddev.geo.controllers;

import com.wilddev.geo.controllers.responses.LocationResponse;
import com.wilddev.geo.services.LocationService;

import fun.wilddev.spring.web.controllers.AbstractController;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RequestMapping("/location")
@RestController
@AllArgsConstructor
public class LocationController extends AbstractController {

    private final LocationService locationService;

    @GetMapping
    public ResponseEntity<?> detect(@RequestHeader("X-Real-IP") String ip) {
        return locationService.detect(ip).map(LocationResponse::new)
                .map(this::ok).orElse(notFound());
    }
}
