package com.ysb5397.hybrid_map._global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HttpConfig {

    private static RestTemplate restTemplate;
    private static HttpHeaders headers = new HttpHeaders();

    @Bean
    public RestTemplate restTemplate() {
        this.restTemplate = new RestTemplate();
        return restTemplate;
    }

    // POST
    public static <T> ResponseEntity<?> post(String url, T requestData) {
        HttpEntity<String> entity = new HttpEntity<String>(requestData.toString(), headers);
        ResponseEntity<?> response = restTemplate.exchange(url, HttpMethod.POST, entity, ResponseEntity.class);
        System.out.println(response);
        return response;
    }

    // GET

    // PUT

    // DELETE
}
