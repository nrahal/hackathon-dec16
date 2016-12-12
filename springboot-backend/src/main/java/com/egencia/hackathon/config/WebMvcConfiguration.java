package com.egencia.hackathon.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Spring {@link Configuration} element for Spring MVC.
 *
 * <p>
 * This {@link Configuration} bean is responsible for setting up the Spring MVC configuration, such as to register
 * {@link HttpMessageConverter}s and Spring MVC interceptors.
 * </p>
 *
 * @author Remy CREPIN
 *
 */
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

    @Inject
    private ObjectMapper defaultObjectMapper;

    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> messageConverters) {
        messageConverters.addAll(buildMessageConverters());
    }

    @Override
    protected void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.APPLICATION_JSON);
    }

    @Bean
    public List<HttpMessageConverter<?>> buildMessageConverters() {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter jackson2Converter = new MappingJackson2HttpMessageConverter();
        jackson2Converter.setObjectMapper(defaultObjectMapper);
        messageConverters.add(jackson2Converter);
        return messageConverters;
    }


}
