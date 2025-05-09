package fun.wilddev.geo.services;

import fun.wilddev.geo.entities.LocationD;
import fun.wilddev.geo.repositories.LocationRepository;

import java.util.List;

import lombok.AllArgsConstructor;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class LocationService {

    private final LocationRepository locationRepository;

    public List<LocationD> list(@NonNull String qualifier) {
        return locationRepository.findTop10ByQualifierStartsWithOrderByCity(qualifier);
    }
}
