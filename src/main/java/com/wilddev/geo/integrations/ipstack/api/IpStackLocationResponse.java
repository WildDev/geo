package com.wilddev.geo.integrations.ipstack.api;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Setter
@Getter
@ToString
public class IpStackLocationResponse {

    @JsonProperty
    private Boolean success;

    @JsonUnwrapped
    private IpStackCountryResponse country;

    @JsonProperty
    private String city;

    @JsonProperty
    private IpStackErrorResponse error;
}
