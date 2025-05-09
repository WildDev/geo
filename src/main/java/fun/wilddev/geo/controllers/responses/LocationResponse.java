package fun.wilddev.geo.controllers.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import fun.wilddev.geo.models.Location;

import lombok.*;

@Setter
@Getter
@ToString
public class LocationResponse {

    @JsonProperty
    private CountryResponse country;

    @JsonProperty
    private String city;

    public LocationResponse(@NonNull Location location) {

        this.country = new CountryResponse(location.country());
        this.city = location.city();
    }
}
