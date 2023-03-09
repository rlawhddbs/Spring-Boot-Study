package com.example.board.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("Back-End Study API Document")
                .version("v0.0.1")
                .description("For Back-End study with API Document");
        return new OpenAPI()
                .components(new Components())
                .info(info);
    }

}
