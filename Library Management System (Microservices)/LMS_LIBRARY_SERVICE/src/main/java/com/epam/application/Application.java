package com.epam.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@SpringBootApplication
@ComponentScan(basePackages = "com.epam")
@EnableJpaRepositories(basePackages = "com.epam")
@EnableTransactionManagement
@EntityScan(basePackages = "com.epam")
@EnableSwagger2
@EnableFeignClients(basePackages = "com.epam")
public class Application
{
    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Docket lmsAPI()
    {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any()).build().apiInfo(getDocumentInfo());
    }

    public ApiInfo getDocumentInfo()
    {
        ApiInfo apiInfo = new ApiInfo(
                "Library Management System API",
                "API for Library Management System",
                "2.1",
                "Terms of service",
                new Contact("Manash Rauta", "http://localhost:9002/lms", "manash_rauta@epam.com"),
                "Apache License Vesion",
                "http://apache.com", new ArrayList());
        return apiInfo;
    }
}
