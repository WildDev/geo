package fun.wilddev.geo.integrations.ipstack;

import fun.wilddev.geo.exceptions.HttpRequestFailedException;
import fun.wilddev.geo.interfaces.LocationFind;

import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import lombok.val;

import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import fun.wilddev.geo.integrations.ipstack.api.*;
import fun.wilddev.geo.models.*;

import org.springframework.lang.*;
import org.springframework.web.client.*;

@RegisterReflectionForBinding({
        IpStackCountryResponse.class,
        IpStackLocationResponse.class,
        IpStackErrorResponse.class
})

@ConditionalOnProperty(name = "mode", havingValue = "ipstack", matchIfMissing = true)
@Service
@Slf4j
public class IpStackHttp extends IpStackHttpService implements LocationFind {

    public IpStackHttp(RestClient restClient) {
        super(restClient);
    }

    private Country map(@Nullable IpStackCountryResponse response) {
        return response == null ? null : new Country(response.getCode(), response.getName());
    }

    private Location map(@Nullable IpStackLocationResponse response) {
        return response == null ? null : new Location(map(response.getCountry()), response.getCity());
    }

    @Override
    public Location find(@NonNull String ip) throws HttpRequestFailedException {

        try {
            return restClient.get().uri("/{ip}?access_key={accessKey}",
                    Map.of("ip", ip)).exchange((request, response) -> {

                val source = response.bodyTo(IpStackLocationResponse.class);

                if (source.getSuccess() == Boolean.FALSE)
                    throw new HttpRequestFailedException("Request rejected: " + source);

                return map(source);
            });

        } catch (Exception ex) {
            throw new HttpRequestFailedException(ex.getMessage(), ex);
        }
    }
}
