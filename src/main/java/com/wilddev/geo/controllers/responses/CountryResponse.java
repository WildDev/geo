package com.wilddev.geo.controllers.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wilddev.geo.models.Country;

import lombok.*;

@Setter
@Getter
@ToString
public class CountryResponse {

    @JsonProperty
    private String code;

    @JsonProperty
    private String name;

    public CountryResponse(Country country) {

        this.code = country.code();
        this.name = country.name();
    }
}
