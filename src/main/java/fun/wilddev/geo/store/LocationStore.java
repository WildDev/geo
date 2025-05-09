package fun.wilddev.geo.store;

import fun.wilddev.geo.models.Location;

import java.util.List;

public interface LocationStore {

    List<Location> list(String qualifier);
}
