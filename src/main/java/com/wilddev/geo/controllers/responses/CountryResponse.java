package com.wilddev.geo.controllers.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wilddev.geo.models.Country;

import org.springframework.lang.NonNull;

import lombok.*;

@Setter
@Getter
@ToString
public class CountryResponse {

    @JsonProperty
    private String code;

    @JsonProperty
    private String name;

    public CountryResponse(@NonNull Country country) {

        this.code = country.code();
        this.name = country.name();
    }
}
