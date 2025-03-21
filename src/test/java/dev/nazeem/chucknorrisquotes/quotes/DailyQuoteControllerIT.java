package dev.nazeem.chucknorrisquotes.quotes;

import dev.nazeem.chucknorrisquotes.AbstractBaseIT;
import dev.nazeem.chucknorrisquotes.quotes.data.QuoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.assertj.MvcTestResult;

import java.time.Clock;
import java.time.Instant;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static dev.nazeem.chucknorrisquotes.fixtures.QuoteFixtures.DAILY_QUOTE;
import static dev.nazeem.chucknorrisquotes.quotes.QuotesControllerIT.RESPONSE_PATH;
import static dev.nazeem.chucknorrisquotes.quotes.api.QuotesController.PATH;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class DailyQuoteControllerIT extends AbstractBaseIT {

    @Autowired
    Clock clock;

    @Autowired
    private QuoteRepository quoteRepository;

    @Autowired
    private MockMvcTester mockMvc;

    @BeforeEach
    void setUp() {
        quoteRepository.deleteAll();
    }

    @Test
    void returnQuoteFromDatabaseWhenPresent() {
        DAILY_QUOTE.setCreatedAt(Instant.now(clock));
        quoteRepository.save(DAILY_QUOTE);

        final MvcTestResult response = mockMvc.get()
                .uri(PATH.concat("/daily-quote"))
                .exchange();

        assertThat(response)
                .hasStatusOk()
                .hasContentTypeCompatibleWith(APPLICATION_JSON_VALUE)
                .bodyJson()
                .isStrictlyEqualTo(RESPONSE_PATH.formatted("getDailyQuote-from-db.json"));

        verify(exactly(0), getRequestedFor(urlEqualTo("/jokes/random")));
    }

    @Test
    void returnQuoteFromApiWhenNoQuotePresentInDatabase() {
        final MvcTestResult response = mockMvc.get()
                .uri(PATH.concat("/daily-quote"))
                .exchange();

        assertThat(response)
                .hasStatusOk()
                .hasContentTypeCompatibleWith(APPLICATION_JSON_VALUE)
                .bodyJson()
                .isStrictlyEqualTo(RESPONSE_PATH.formatted("getDailyQuote-from-api.json"));

        verify(exactly(1), getRequestedFor(urlEqualTo("/jokes/random")));
    }

}
