package com.ms.rest.conf.swagger;

import io.swagger.models.auth.In;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.net.HttpHeaders.AUTHORIZATION;

@EnableOpenApi
@Configuration
public class SwaggerConfiguration {
    @Bean
    public Docket swagger() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("default")
                .apiInfo(new ApiInfoBuilder().title("API").version("1.0.0").build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ms.api"))
                .build()
                .securitySchemes(securitySchemes());
    }

    @Bean
    public Docket swaggerAdmin() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("admin")
                .apiInfo(new ApiInfoBuilder().title("vinka admin API").version("1.0.0").build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ms.adminapi"))
                .build()
                .securitySchemes(securitySchemes());
    }

    private List<SecurityScheme> securitySchemes() {
        List<SecurityScheme> parameters = new ArrayList<>();
        parameters.add(new ApiKey("BASE_TOKEN", AUTHORIZATION, In.HEADER.toValue()));
        return parameters;
    }

}
