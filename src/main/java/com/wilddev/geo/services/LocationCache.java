package com.wilddev.geo.services;

import com.wilddev.geo.store.LocationStore;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.wilddev.geo.models.*;
import java.util.*;

@AllArgsConstructor
@Service
@Slf4j
public class LocationCache {

    private final LocationDetector locationDetector;

    private final LocationStore locationStore;

    @Cacheable(cacheNames = "location", unless = "#result == null")
    public Optional<Location> detect(@NonNull String ip) {

        try {
            return Optional.ofNullable(locationDetector.detect(ip));
        } catch (Exception ex) {
            log.error("Failed to detect location of '{}':", ip, ex);
        }

        return Optional.empty();
    }

    @Cacheable("location-list")
    public List<Location> list(@NonNull String qualifier) {
        return locationStore.list(qualifier);
    }
}
