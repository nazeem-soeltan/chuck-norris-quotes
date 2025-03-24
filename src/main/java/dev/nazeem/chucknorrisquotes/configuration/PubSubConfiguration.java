package dev.nazeem.chucknorrisquotes.configuration;

import com.google.api.gax.core.CredentialsProvider;
import com.google.api.gax.core.NoCredentialsProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class PubSubConfiguration {

    public static final String QUOTE_TOPIC = "quotes";
    public static final String DAILY_QUOTE_TOPIC = "daily-quote";

    @Bean
    @Profile("local")
    CredentialsProvider noCredentialsProvider() {
        return NoCredentialsProvider.create();
    }

}
