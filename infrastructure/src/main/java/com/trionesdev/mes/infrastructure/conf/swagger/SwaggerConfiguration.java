package com.trionesdev.mes.infrastructure.conf.swagger;

import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.customizers.OpenApiCustomiser;
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
                .packagesToScan("ms.triones.backend.rest.backend")
                .addOpenApiCustomiser(authorizationOpenApiCustomiser())
                .build();
    }

    public OpenApiCustomiser authorizationOpenApiCustomiser() {
        return openApi -> openApi.schemaRequirement("AUTHORIZATION", new SecurityScheme().type(SecurityScheme.Type.APIKEY)
                        .in(SecurityScheme.In.HEADER)
                        .name(AUTHORIZATION))
                .addSecurityItem(new SecurityRequirement().addList("AUTHORIZATION"))
                ;
    }

}
