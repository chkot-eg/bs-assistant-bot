package no.eg.bsassistantbot.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalApiService {

    private final RestTemplate restTemplate;

    public ExternalApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String callExternalApi(String url) {
        // Token automatically added by platform
        return restTemplate.getForObject(url, String.class);
    }
}
