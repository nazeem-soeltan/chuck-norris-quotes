package dev.nazeem.chucknorrisquotes.quotes;

import java.time.Clock;
import java.time.Instant;
import java.util.List;
import java.util.stream.IntStream;

import dev.nazeem.chucknorrisquotes.quotes.data.Quote;
import dev.nazeem.chucknorrisquotes.quotes.data.QuoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import dev.nazeem.chucknorrisquotes.client.ChuckNorrisJokesClient;
import dev.nazeem.chucknorrisquotes.client.JokesResponse;
import lombok.RequiredArgsConstructor;


@Slf4j
@Service
@RequiredArgsConstructor
public class QuotesService {

    private final Clock clock;
    private final QuoteRepository quoteRepository;
    private final ChuckNorrisJokesClient chuckNorrisJokesClient;

    public List<Quote> getRandomQuotes(int maxQuotes) {
        return IntStream.range(0, maxQuotes)
                .mapToObj(num -> fetchJoke())
                .map(Quote::from)
                .toList();
    }

    public Quote getDailyQuote() {
        return quoteRepository.findQuoteForToday(clock)
                .orElseGet(this::fetchAndSaveQuote);
    }

    private Quote fetchAndSaveQuote() {
        final var response = fetchJoke();

        final var quote = Quote.from(response);
        quote.setCreatedAt(Instant.now(clock));

        return quoteRepository.save(quote);
    }

    private JokesResponse fetchJoke() {
        try {
            log.info("Attempt fetching joke ...");
            return chuckNorrisJokesClient.getRandomJoke();
        } catch (final Exception e) {
            log.error("Unable to fetch joke", e);
            throw new QuoteNotAvailableException();
        }
    }

}
