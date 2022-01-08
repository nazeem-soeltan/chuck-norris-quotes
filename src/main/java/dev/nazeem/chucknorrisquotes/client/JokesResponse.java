package dev.nazeem.chucknorrisquotes.client;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class JokesResponse {

    @Builder.Default
    List<String> categories = new ArrayList<>();

    @JsonProperty("created_at")
    String createdAt;

    @JsonProperty("icon_url")
    String iconUrl;

    String id;

    @JsonProperty("updated_at")
    String updatedAt;

    String url;

    String value;

}
