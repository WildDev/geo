package com.wilddev.geo.controllers.responses;

import com.wilddev.geo.models.Location;

import lombok.*;

@Setter
@Getter
@ToString
public class LocationResponse {

    private CountryResponse country;

    private String city;

    public LocationResponse(Location location) {

        this.country = new CountryResponse(location.country());
        this.city = location.city();
    }
}
