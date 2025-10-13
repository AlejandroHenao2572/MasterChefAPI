package com.masterchef.MasterChefAPI.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    
    @Bean
    public OpenAPI recipeManagementOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("API de Gestion de Recetas - DOSW Company")
                .description("API REST para la gestión de recetas de cocina del programa MasterChef")
                .version("1.0.0"));
    }
    
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("recipes-api")
                .pathsToMatch("/api/**")
                .build();
    }
}