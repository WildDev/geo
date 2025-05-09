package fun.wilddev.geo.integrations;

import lombok.AllArgsConstructor;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
public abstract class HttpService {

    protected final String url;

    protected final RestTemplate restTemplate;
}
