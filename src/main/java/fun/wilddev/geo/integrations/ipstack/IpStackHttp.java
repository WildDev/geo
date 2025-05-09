package fun.wilddev.geo.integrations.ipstack;

import fun.wilddev.geo.exceptions.HttpRequestFailedException;
import fun.wilddev.geo.interfaces.LocationFind;

import lombok.extern.slf4j.Slf4j;
import lombok.val;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import fun.wilddev.geo.integrations.ipstack.api.*;
import fun.wilddev.geo.models.*;

import org.springframework.http.*;
import org.springframework.lang.*;
import org.springframework.web.client.*;

@ConditionalOnProperty(name = "mode", havingValue = "ipstack", matchIfMissing = true)
@Service
@Slf4j
public class IpStackHttp extends IpStackHttpService implements LocationFind {

    public IpStackHttp(@Value("${integration.ipstack.url}") String url,
                       @Value("${integration.ipstack.access-key}") String accessKey,
                       RestTemplate restTemplate) {
        super(url, accessKey, restTemplate);
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
            ResponseEntity<IpStackLocationResponse> responseEntity = restTemplate
                    .getForEntity(url + "/{ip}?access_key={accessKey}", IpStackLocationResponse.class, ip, accessKey);

            HttpStatusCode httpStatusCode = responseEntity.getStatusCode();

            if (httpStatusCode.is2xxSuccessful()) {

                val response = responseEntity.getBody();

                if (response == null)
                    throw new HttpRequestFailedException("null response");

                if (response.getSuccess() == Boolean.FALSE)
                    throw new HttpRequestFailedException("Request rejected: " + response.getError());

                return map(response);
            }

            throw new HttpRequestFailedException("Unhandled http code " + httpStatusCode);

        } catch (RestClientException ex) {

            if (ex instanceof HttpClientErrorException httpEx)
                log.error("Response report: {}", httpEx.getResponseBodyAsString());

            throw new HttpRequestFailedException("Request failed", ex);
        }
    }
}
