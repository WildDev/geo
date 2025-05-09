package fun.wilddev.geo.controllers;

import fun.wilddev.geo.cache.LocationCache;
import fun.wilddev.geo.controllers.responses.LocationResponse;
import fun.wilddev.spring.web.controllers.AbstractController;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RequestMapping("/location")
@RestController
@AllArgsConstructor
public class LocationController extends AbstractController {

    private final LocationCache locationCache;

    @GetMapping("/find")
    public ResponseEntity<?> find(@RequestHeader("X-Real-IP") String ip) {
        return locationCache.find(ip).map(LocationResponse::new)
                .map(this::ok).orElse(notFound());
    }

    @GetMapping("/list")
    public ResponseEntity<?> list(@RequestParam("q") String qualifier) {
        return ok(locationCache.list(qualifier).stream().map(LocationResponse::new).toList());
    }
}
