package com.egencia.hackathon.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring {@link org.springframework.context.annotation.Configuration} element for Jackson 2.
 * 
 * <p>
 * This {@link org.springframework.context.annotation.Configuration} bean is responsible for creating a default Jackson 2's {@link com.fasterxml.jackson.databind.ObjectMapper} that is configured to meet the API standards,
 * such as to:
 * <ul>
 * <li>silently apply ECMAScript naming conventions,</li>
 * <li>only include non null properties at marshalling time,</li>
 * <li>ignore unknown (unmapped) properties.</li>
 * </ul>
 * </p>
 * 
 * @author Remy CREPIN
 * 
 */
@Configuration
public class JacksonConfiguration {

    @Bean
    public ObjectMapper defaultObjectMapper() {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper;
    }

}