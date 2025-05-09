package fun.wilddev.geo.integrations.ipstack.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@Setter
@Getter
@ToString
public class IpStackErrorResponse {

    @JsonProperty
    private String code;

    @JsonProperty
    private String type;

    @JsonProperty
    private String info;
}
