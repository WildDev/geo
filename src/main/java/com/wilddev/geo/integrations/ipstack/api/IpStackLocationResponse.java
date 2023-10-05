package com.wilddev.geo.integrations.ipstack.api;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Setter
@Getter
@ToString
public class IpStackLocationResponse {

    @JsonUnwrapped
    private IpStackCountryResponse country;

    @JsonProperty
    private String city;
}
