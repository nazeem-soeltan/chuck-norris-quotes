package dev.nazeem.chucknorrisquotes.local;

import dev.nazeem.chucknorrisquotes.Application;
import org.springframework.boot.SpringApplication;

public class LocalApplication {

    public static void main(String[] args) {
        SpringApplication.from(Application::main)
                .with(LocalConfiguration.class)
                .withAdditionalProfiles("local")
                .run(args);
    }

}
