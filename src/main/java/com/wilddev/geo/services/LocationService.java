package com.wilddev.geo.services;

import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.wilddev.geo.models.*;

@AllArgsConstructor
@Service
@Slf4j
public class LocationService {

    private final LocationDetector locationDetector;

    @Cacheable(cacheNames = "location", unless = "#result == null")
    public Optional<Location> detect(@NonNull String ip) {

        try {
            return Optional.ofNullable(locationDetector.detect(ip));
        } catch (Exception ex) {
            log.error("Failed to detect location of '{}':", ip, ex);
        }

        return Optional.empty();
    }
}
