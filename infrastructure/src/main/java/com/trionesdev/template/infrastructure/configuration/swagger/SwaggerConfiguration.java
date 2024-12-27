package com.trionesdev.template.infrastructure.configuration.swagger;

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
    public GroupedOpenApi tenantApi() {
        return GroupedOpenApi.builder()
                .group("Triones Admin Tenant API")
                .pathsToMatch("/tenant-api/**")
                .packagesToScan("com.trionesdev.template.rest.tenant")
                .addOpenApiCustomizer(authorizationOpenApiCustomiser())
                .build();
    }

    public OpenApiCustomizer authorizationOpenApiCustomiser() {
        return openApi -> openApi
                .info(new Info().title("Triones Admin API"))
                .schemaRequirement("AUTHORIZATION", new SecurityScheme().type(SecurityScheme.Type.APIKEY)
                        .in(SecurityScheme.In.HEADER)
                        .name(AUTHORIZATION))
                .addSecurityItem(new SecurityRequirement().addList("AUTHORIZATION"))
                ;
    }

}
