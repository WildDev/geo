package com.wilddev.geo.integrations.ipstack;

import com.wilddev.geo.integrations.HttpService;
import org.springframework.web.client.RestTemplate;

public abstract class IpStackHttpService extends HttpService {

    protected final String accessKey;

    protected IpStackHttpService(String url, String accessKey, RestTemplate restTemplate) {

        super(url, restTemplate);
        this.accessKey = accessKey;
    }
}
