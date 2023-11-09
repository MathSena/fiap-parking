package com.fiap.fiaparking.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .info(new Info().title("Power Save REST API")
                        .description("REST API  for registration of People, Addresses, and Appliances.\n" +
                                "The purpose of this system is to calculate monthly energy consumption.")
                        .license(new License().name("Apache 2.0").url("https://spring.org")
                        )
                ).externalDocs(new ExternalDocumentation()
                        .description("Github Repository")
                        .url("https://github.com/MathSena/fiap-parking"));
    }
}
