package fun.wilddev.geo.cache;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import fun.wilddev.geo.interfaces.*;
import fun.wilddev.geo.models.*;
import java.util.*;

@AllArgsConstructor
@Component
@Slf4j
public class LocationCache {

    private final LocationFind locationFind;

    private final LocationList locationList;

    @Cacheable(cacheNames = "location-find", unless = "#result == null")
    public Optional<Location> find(@NonNull String ip) {

        try {
            return Optional.ofNullable(locationFind.find(ip));
        } catch (Exception ex) {
            log.error("Failed to find the location of '{}':", ip, ex);
        }

        return Optional.empty();
    }

    @Cacheable("location-list")
    public List<Location> list(@NonNull String qualifier) {
        return locationList.list(qualifier);
    }
}
