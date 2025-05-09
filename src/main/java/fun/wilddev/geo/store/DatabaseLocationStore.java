package fun.wilddev.geo.store;

import fun.wilddev.geo.models.Location;
import fun.wilddev.geo.repositories.LocationRepository;

import java.util.List;

import lombok.AllArgsConstructor;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DatabaseLocationStore implements LocationStore {

    private final LocationRepository locationRepository;

    @Override
    public List<Location> list(@NonNull String qualifier) {
        return locationRepository.findTop10ByQualifierStartsWithOrderByCity(qualifier)
                .stream().map(e -> new Location(e.getCountry(), e.getCity())).toList();
    }
}
