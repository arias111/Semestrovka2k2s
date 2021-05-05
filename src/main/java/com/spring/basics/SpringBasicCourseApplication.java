package com.spring.basics;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
@EnableJpaRepositories
@Configuration
@Component
@EntityScan(basePackages = "com.spring.basics.models")
public class SpringBasicCourseApplication {
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        return factory.createMultipartConfig();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

        public static void main (String[]args){
            SpringApplication.run(SpringBasicCourseApplication.class, args);
        }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
