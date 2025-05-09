package fun.wilddev.geo.integrations.ipstack.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@Setter
@Getter
@ToString
public class IpStackCountryResponse {

    @JsonProperty("country_code")
    private String code;

    @JsonProperty("country_name")
    private String name;
}
