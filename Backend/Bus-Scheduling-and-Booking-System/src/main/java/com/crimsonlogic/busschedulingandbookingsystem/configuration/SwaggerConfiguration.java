package com.crimsonlogic.busschedulingandbookingsystem.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

 

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
@Configuration
public class SwaggerConfiguration {

    private ApiInfo apiInfo() {
        return new ApiInfo("Passport MS REST APIs",
                "REST APIs for Bus Booking and Scheduling MS Application",
                "1.0",
                "Terms of service",
                new Contact("CrimsonLogic", "www.crimsonlogic.com", ""),
                "License of API",
                "API license URL",
                Collections.emptyList());
    }

 

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
}