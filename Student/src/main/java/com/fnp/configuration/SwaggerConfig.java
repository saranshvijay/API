package com.fnp.configuration;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	
	private ApiInfo apiInfo() {
        return new ApiInfo("Student Api",
                "REST APIs for Student Database",
                "1.0",
                "Terms of service",
                new springfox.documentation.service.Contact("Saransh", "www.fnp.com", "saransh.v@fnp.com"),
                "License of API",
                "API license URL",
                Collections.emptyList());
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.fnp"))
                .paths(PathSelectors.any())                
                .build();
    }
}
