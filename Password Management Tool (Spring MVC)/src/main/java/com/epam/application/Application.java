package com.epam.application;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@ComponentScan(basePackages = "com.epam")
@EnableJpaRepositories(basePackages = "com.epam")
@EnableTransactionManagement
@EntityScan(basePackages = "com.epam")
@EnableSwagger2
public class Application
{
    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Docket pmtAPI()
    {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any()).build().apiInfo(getDocumentInfo());
    }

    public ApiInfo getDocumentInfo()
    {
        ApiInfo  apiInfo = new ApiInfo(
                "Password Management Service API",
                "API for Password Management Service",
                "2.1",
                "Terms of service",
                new Contact("Manash Rauta","http://localhost:9000/pmt","manash_rauta@epam.com"),
                "Apache License Vesion",
                "http://apache.com",new ArrayList());
        return apiInfo;
    }

}