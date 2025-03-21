package dev.nazeem.chucknorrisquotes.quotes.data;

import dev.nazeem.chucknorrisquotes.client.JokesResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "quotes")
public class Quote {

    @Id
    public String id;

    public String description;

    public String reference;

    public Instant createdAt;

    public static Quote from(final JokesResponse jokeResponse) {
        return Quote.builder()
                .description(jokeResponse.value())
                .reference(jokeResponse.id())
                .build();
    }

}
