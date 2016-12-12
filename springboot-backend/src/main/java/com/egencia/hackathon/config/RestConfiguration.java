package com.egencia.hackathon.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class RestConfiguration {

    @Inject
    private ObjectMapper objectMapper;

    @Bean
    public RestTemplate restTemplate() {
        final MappingJackson2HttpMessageConverter jackson2Converter = new MappingJackson2HttpMessageConverter();
        jackson2Converter.setObjectMapper(objectMapper);
        List<HttpMessageConverter<?>> converters = new ArrayList<>();
        converters.add(new FormHttpMessageConverter());
        converters.add(new StringHttpMessageConverter());
        converters.add(new ByteArrayHttpMessageConverter());
        converters.add(jackson2Converter);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(converters);
        return restTemplate;
    }

}