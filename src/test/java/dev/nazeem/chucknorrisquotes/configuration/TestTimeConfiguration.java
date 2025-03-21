package dev.nazeem.chucknorrisquotes.configuration;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

@TestConfiguration
public class TestTimeConfiguration {

    private static final String TIME = "2025-01-01T13:00:00.00Z";

    @Bean
    @Primary
    Clock fixedClock() {
        return Clock.fixed(Instant.parse(TIME),
                ZoneId.systemDefault());
    }

}
