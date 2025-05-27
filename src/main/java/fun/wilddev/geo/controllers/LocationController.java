package fun.wilddev.geo.controllers;

import fun.wilddev.geo.cache.LocationCache;
import fun.wilddev.spring.web.controllers.AbstractController;

import lombok.AllArgsConstructor;

import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;
import org.springframework.http.ResponseEntity;

import fun.wilddev.geo.controllers.responses.*;
import org.springframework.web.bind.annotation.*;

@RegisterReflectionForBinding({
        LocationResponse.class,
        CountryResponse.class
})

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
