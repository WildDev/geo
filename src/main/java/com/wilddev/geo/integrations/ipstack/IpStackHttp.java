package com.wilddev.geo.integrations.ipstack;

import com.wilddev.geo.exceptions.HttpRequestFailedException;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import com.wilddev.geo.integrations.ipstack.api.*;
import com.wilddev.geo.models.*;
import org.springframework.http.*;
import org.springframework.web.client.*;

@Slf4j
@ConditionalOnProperty(name = "app.location.detector", havingValue = "ipstack", matchIfMissing = true)
@Service
public class IpStackHttp extends IpStackHttpService implements LocationDetector {

    public IpStackHttp(@Value("${app.integrations.ipstack.url}") String url,
                       @Value("${app.integrations.ipstack.accessKey}") String accessKey,
                       RestTemplate restTemplate) {
        super(url, accessKey, restTemplate);
    }

    @Override
    public Location detect(String ip) throws HttpRequestFailedException {

        try {
            ResponseEntity<IpStackLocationResponse> responseEntity = restTemplate
                    .getForEntity(url + "/{ip}?access_key={accessKey}", IpStackLocationResponse.class, ip, accessKey);

            HttpStatusCode httpStatusCode = responseEntity.getStatusCode();

            if (httpStatusCode.is2xxSuccessful())
                return map(responseEntity.getBody());

            throw new HttpClientErrorException(httpStatusCode, "Unhandled http code");

        } catch (RestClientException ex) {

            if (ex instanceof HttpClientErrorException)
                log.error("Wrong response: {}", ((HttpClientErrorException) ex).getResponseBodyAsString());

            throw new HttpRequestFailedException("Http request failed", ex);
        }
    }

    private Country map(@Nullable IpStackCountryResponse response) {
        return response == null ? null : new Country(response.getCode(), response.getName());
    }

    private Location map(@Nullable IpStackLocationResponse response) {
        return response == null ? null : new Location(map(response.getCountry()), response.getCity());
    }
}
