package com.trionesdev.mes.infrastructure.conf.swagger;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public GroupedOpenApi backendApi() {
        return GroupedOpenApi.builder()
                .group("Triones Backend API")
                .pathsToMatch("/api/**")
                .packagesToScan("com.trionesdev.mes.rest.backend")
                .addOpenApiCustomizer(authorizationOpenApiCustomiser())
                .build();
    }

    public OpenApiCustomizer authorizationOpenApiCustomiser() {
        return openApi -> openApi
                .info(new Info().title("Triones Backend API"))
                .schemaRequirement("AUTHORIZATION", new SecurityScheme().type(SecurityScheme.Type.APIKEY)
                        .in(SecurityScheme.In.HEADER)
                        .name(AUTHORIZATION))
                .addSecurityItem(new SecurityRequirement().addList("AUTHORIZATION"))
                ;
    }

}
