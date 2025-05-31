package fun.wilddev.geo.integrations.ipstack;

import fun.wilddev.geo.integrations.HttpService;
import org.springframework.web.client.RestClient;

public abstract class IpStackHttpService extends HttpService {

    protected IpStackHttpService(RestClient restClient) {
        super(restClient);
    }
}
