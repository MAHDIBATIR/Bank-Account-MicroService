package com.example.microservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI compteOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Gestion des Comptes Bancaires")
                        .description("API REST pour la gestion des comptes bancaires - Micro-service")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Équipe de Développement")
                                .email("contact@example.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://www.apache.org/licenses/LICENSE-2.0.html")));
    }
}

