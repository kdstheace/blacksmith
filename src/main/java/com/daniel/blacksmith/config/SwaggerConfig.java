package com.daniel.blacksmith.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerConfig {

    private ApiInfo apiInfo(){
        return new ApiInfo(
            "Blacksmith RESTful API",
            "Springboot Blog API",
            "1",
            "Terms of Service",
            new Contact("Dongsoo Kim","www.daniel.net", "kdstheace@naver.com"),
            "License of API",
            "API License URL",
            Collections.emptyList()
        );//title, description, version, Terms of Service, Contact Info, license of API, API License URL
    }

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.any())// look for any apis OR .apis(RequestHandlerSelectors.basePackage("postController"))
            .paths(PathSelectors.any()) //.paths(PathSelectors.regex("url"))
            .build();
    }
}
