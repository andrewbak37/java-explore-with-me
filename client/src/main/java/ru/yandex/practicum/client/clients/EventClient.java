package ru.yandex.practicum.client.clients;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@PropertySource("classpath:application.properties")
public class EventClient {

    private final RestTemplate restStat;

    private static final String APP = "ewm-main-service";
    private static final String API_PREFIX_HIT = "/hit";
    private static final String API_PREFIX_STAT = "/stats";
    private static final String API_PREFIX_EVENT = "/events";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Value("${explore-with-me-stats.url}")
    private String serverStatUrl;

     @Autowired
    public EventClient(RestTemplateBuilder builder) {
        builder.uriTemplateHandler(new DefaultUriBuilderFactory(serverStatUrl + ""));
        restStat = builder.build();
    }


    public void postStat(String uri, String ip) {
        EndpointHit endpointHit = new EndpointHit(APP, uri, ip, LocalDateTime.now());
        HttpEntity<EndpointHit> body = new HttpEntity<>(endpointHit, defaultHeaders());
        restStat.exchange(serverStatUrl + API_PREFIX_HIT, HttpMethod.POST, body, Object.class);
    }

    public ResponseEntity<List<ViewStats>> getStats(String start, String end, List<String> uris, Boolean unique) {
        String requestParam = makeRequestPath(start, end, uris, unique);
        HttpEntity<EndpointHit> body = new HttpEntity<>(null, defaultHeaders());
        String pathRequest = serverStatUrl + API_PREFIX_STAT + requestParam;
        return restStat.exchange(pathRequest,
                HttpMethod.GET,
                body,
                new ParameterizedTypeReference<>() {
                },
                Collections.emptyMap());
    }

    public Long getEventViews(LocalDateTime publishedOn, Long eventId) {
        List<ViewStats> viewStats = Objects.requireNonNull(
                getStats(
                    publishedOn.format(formatter),
                    LocalDateTime.now().format(formatter),
                    List.of(makeEventUri(eventId)),
                    false
                ).getBody());
        if (viewStats.isEmpty()) {
            return 0L;
        }
        return viewStats.get(0).getHits();
    }

    private String makeEventUri(Long eventId) {
        return API_PREFIX_EVENT + eventId;
    }

    private HttpHeaders defaultHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        return headers;
    }

    private String makeRequestPath(String start, String end, List<String> uris, Boolean unique) {
        StringBuilder request = new StringBuilder();

        if (start != null) {
            request.append("?start=").append(URLEncoder.encode(start, StandardCharsets.UTF_8));
        }
        if (end != null) {
            request.append("&end=").append(URLEncoder.encode(end, StandardCharsets.UTF_8));
        }
        if (uris != null && !uris.isEmpty()) {
            request.append("&uri=").append(uris.get(0));
            for (int i = 1; i < uris.size(); i++) {
                request.append(",").append("uri=").append(uris.get(i));
            }
        }
        if (unique != null) {
            request.append("&unique=").append(unique);
        }
        return request.toString();
    }
}