package dev.nazeem.chucknorrisquotes.quotes;

import java.util.List;
import java.util.stream.IntStream;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import dev.nazeem.chucknorrisquotes.client.ChuckNorrisJokesClient;
import dev.nazeem.chucknorrisquotes.client.JokesResponse;
import lombok.RequiredArgsConstructor;


@Slf4j
@Service
@RequiredArgsConstructor
public class QuotesService {

    private final ChuckNorrisJokesClient chuckNorrisJokesClient;

    public List<Quote> getRandomQuotes(int maxQuotes) {
        return IntStream.range(0, maxQuotes)
                .mapToObj(num -> fetchJoke())
                .map(Quote::from)
                .toList();
    }

    private JokesResponse fetchJoke() {
        try {
            return chuckNorrisJokesClient.getRandomJoke();
        } catch (final Exception e) {
            log.error("Unable to fetch joke", e);
            throw new QuoteNotAvailableException();
        }
    }

}
