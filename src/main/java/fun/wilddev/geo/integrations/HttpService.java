package fun.wilddev.geo.integrations;

import lombok.AllArgsConstructor;
import org.springframework.web.client.RestClient;

@AllArgsConstructor
public abstract class HttpService {

    protected final RestClient restClient;
}
