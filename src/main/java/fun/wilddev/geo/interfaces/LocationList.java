package fun.wilddev.geo.interfaces;

import fun.wilddev.geo.models.Location;
import java.util.List;

public interface LocationList {

    List<Location> list(String qualifier);
}
