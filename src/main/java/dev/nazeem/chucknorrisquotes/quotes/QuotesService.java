package dev.nazeem.chucknorrisquotes.quotes;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import dev.nazeem.chucknorrisquotes.client.ChuckNorrisJokesClient;
import dev.nazeem.chucknorrisquotes.client.JokesResponse;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class QuotesService {

    private final ChuckNorrisJokesClient chuckNorrisJokesClient;

    public List<Quote> getRandomQuotes(int maxQuotes) {
        return IntStream.range(0, maxQuotes)
                .mapToObj(num -> chuckNorrisJokesClient.getRandomJoke())
                .map(this::buildQuote)
                .toList();
    }

    private Quote buildQuote(final JokesResponse jokesResponse) {
        return Quote.builder()
                .key(jokesResponse.id())
                .text(jokesResponse.value())
                .build();
    }

}
