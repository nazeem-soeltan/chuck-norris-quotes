package dev.nazeem.chucknorrisquotes.quotes;

import static dev.nazeem.chucknorrisquotes.fixtures.ChuckNorrisJokesFixtures.JOKES_RESPONSE;
import static dev.nazeem.chucknorrisquotes.fixtures.QuoteFixtures.QUOTE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import dev.nazeem.chucknorrisquotes.quotes.data.Quote;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.nazeem.chucknorrisquotes.client.ChuckNorrisJokesClient;

@ExtendWith(MockitoExtension.class)
class QuotesServiceTest {

    @Mock
    private ChuckNorrisJokesClient chuckNorrisJokesClient;

    @InjectMocks
    private QuotesService quotesService;

    @Test
    void shouldReturnRequestedAmountOfQuotes() {
        when(chuckNorrisJokesClient.getRandomJoke())
                .thenReturn(JOKES_RESPONSE);

        final List<Quote> quotes = quotesService.getRandomQuotes(1);

        assertThat(quotes)
                .hasSize(1)
                .first()
                .isEqualTo(QUOTE);
    }

    @Test
    void shouldCallJokesClientEqualToProvidedAmountOfMaxQuotes() {
        when(chuckNorrisJokesClient.getRandomJoke())
                .thenReturn(JOKES_RESPONSE);

        quotesService.getRandomQuotes(5);

        verify(chuckNorrisJokesClient, times(5)).getRandomJoke();
    }

}