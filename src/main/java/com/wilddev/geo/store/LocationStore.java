package com.wilddev.geo.store;

import com.wilddev.geo.models.Location;

import java.util.List;

public interface LocationStore {

    List<Location> list(String qualifier);
}
