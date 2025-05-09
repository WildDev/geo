package fun.wilddev.geo.interfaces;

import fun.wilddev.geo.models.Location;

public interface LocationFind {

    Location find(String ip) throws Exception;
}
