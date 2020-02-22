package com.spintech.testtask.service.tmdb.impl;

import com.spintech.testtask.service.tmdb.TmdbApi;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;

@Service
@Slf4j
public class TmdbApiImpl implements TmdbApi {
    @Value("${tmdb.apikey}")
    private String tmdbApiKey;
    @Value("${tmdb.language}")
    private String tmdbLanguage;
    @Value("${tmdb.api.base.url}")
    private String tmdbApiBaseUrl;

    @Override
    public String getPopularTVShows() throws IllegalArgumentException {
        try {
            String url = getTmdbUrl("/tv/popular");

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            if (!response.getStatusCode().is2xxSuccessful()) {
                return null;
            }

            return response.getBody();
        } catch (URISyntaxException e) {
            log.error("Couldn't get popular tv shows");
        }
        return null;
    }

    private String getTmdbUrl(String tmdbItem) throws URISyntaxException {
        URIBuilder builder = new URIBuilder(tmdbApiBaseUrl + tmdbItem);

        builder.addParameter("language", tmdbLanguage);
        builder.addParameter("api_key", tmdbApiKey);
        return builder.build().toString();
    }
}
