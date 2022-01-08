package dev.nazeem.chucknorrisquotes.client;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "ChuckNorrisJokesClient",
        url = "${feign.client.config.ChuckNorrisJokesClient.url:https://api.chucknorris.io}",
        path = "/jokes"
)
public interface ChuckNorrisJokesClient {

    @GetMapping(value = "/random", produces = APPLICATION_JSON_VALUE)
    JokesResponse getRandomJoke();

}
