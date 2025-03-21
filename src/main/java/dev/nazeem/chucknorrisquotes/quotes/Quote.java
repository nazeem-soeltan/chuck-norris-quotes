package dev.nazeem.chucknorrisquotes.quotes;

import dev.nazeem.chucknorrisquotes.client.JokesResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "A Quote uniquely identified by a key.")
public record Quote(String key, String text)
{
    public static Quote from(JokesResponse response) {
        return new Quote(response.id(), response.value());
    }
}
