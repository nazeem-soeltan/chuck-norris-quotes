package dev.nazeem.chucknorrisquotes.client;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;

@Builder
public record JokesResponse(

        List<String> categories,

        @JsonProperty("created_at")
        String createdAt,

        @JsonProperty("icon_url")
        String iconUrl,

        String id,

        @JsonProperty("updated_at")
        String updatedAt,

        String url,

        String value)
{
    public static class JokesResponseBuilder {
        public JokesResponseBuilder() {
            this.categories = List.of();
        }
    }
}
