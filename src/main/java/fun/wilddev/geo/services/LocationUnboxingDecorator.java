package fun.wilddev.geo.services;

import fun.wilddev.geo.interfaces.LocationList;
import fun.wilddev.geo.models.Location;

import java.util.List;

import lombok.AllArgsConstructor;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class LocationUnboxingDecorator implements LocationList {

    private final LocationService locationService;

    @Override
    public List<Location> list(@NonNull String qualifier) {
        return locationService.list(qualifier).stream().map(i -> new Location(i.getCountry(), i.getCity())).toList();
    }
}
