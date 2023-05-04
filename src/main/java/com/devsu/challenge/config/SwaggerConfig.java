package com.devsu.challenge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(subscriptionsApiInfo())
                .select().apis(RequestHandlerSelectors.basePackage("com.devsu.challenge.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo subscriptionsApiInfo() {
        return new ApiInfoBuilder()
                .title("Devsu challenge ")
                .license("MIT - License")
                .description("Devsu Challenge API REST - Bank Movements")
                .version("1.0.0")
                .contact(new Contact("Bryam David Vega Moreno","https://github.com/bvegaM","vegabryam40@gmail.com"))
                .build();
    }
}
