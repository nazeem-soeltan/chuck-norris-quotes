package dev.nazeem.chucknorrisquotes.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .version("1.0")
                        .title("chuck-norris-quotes")
                        .description("Provides Chuck Norris quotes.")
                        .contact(new Contact()
                                .email("github@nazeem.dev")
                                .name("Nazeem Soeltan")
                        )
                );
    }
}
