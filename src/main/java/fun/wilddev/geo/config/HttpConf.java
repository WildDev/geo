package fun.wilddev.geo.config;

import fun.wilddev.geo.exceptions.HttpRequestFailedException;

import java.nio.charset.Charset;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import lombok.val;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;

import org.springframework.context.annotation.*;
import org.springframework.util.*;
import org.springframework.web.client.*;

@Slf4j
@Configuration
public class HttpConf {

    private String formatRequestFailedErrorText(HttpStatusCode code) {
        return "Request failed: code=" + code;
    }

    @Bean
    public RestClient restClient(@Value("${integration.ipstack.url}") String baseUrl,
                                 @Value("${integration.ipstack.access-key}") String accessKey,
                                 @Value("${server.servlet.encoding.charset}") Charset charset) {

        return RestClient.builder()
                .baseUrl(baseUrl)
                .defaultUriVariables(Map.of("accessKey", accessKey))
                .defaultStatusHandler(HttpStatusCode::is3xxRedirection, (request, response) -> {
                    throw new HttpRequestFailedException(formatRequestFailedErrorText(response.getStatusCode()));
                })
                .defaultStatusHandler(HttpStatusCode::isError, (request, response) -> {

                    val code = response.getStatusCode();
                    val responseDump = StreamUtils.copyToString(response.getBody(), charset);

                    log.error("{} response: {}", formatRequestFailedErrorText(code),
                            StringUtils.hasText(responseDump) ? responseDump : "N/A");

                    throw new HttpRequestFailedException(formatRequestFailedErrorText(code));
                })
                .build();
    }
}
