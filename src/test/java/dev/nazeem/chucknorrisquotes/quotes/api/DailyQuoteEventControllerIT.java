package dev.nazeem.chucknorrisquotes.quotes.api;

import dev.nazeem.chucknorrisquotes.AbstractBaseIT;
import dev.nazeem.chucknorrisquotes.quotes.data.QuoteRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.assertj.MvcTestResult;

import java.nio.file.Files;
import java.time.Clock;
import java.time.Instant;

import static com.github.tomakehurst.wiremock.client.WireMock.exactly;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static dev.nazeem.chucknorrisquotes.configuration.PubSubConfiguration.DAILY_QUOTE_TOPIC;
import static dev.nazeem.chucknorrisquotes.fixtures.QuoteFixtures.DAILY_QUOTE;
import static dev.nazeem.chucknorrisquotes.quotes.api.QuotesControllerIT.RESPONSE_PATH;
import static dev.nazeem.chucknorrisquotes.quotes.api.QuotesController.PATH;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


public class DailyQuoteEventControllerIT extends AbstractBaseIT {

    @Autowired
    Clock clock;

    @Autowired
    private QuoteRepository quoteRepository;

    @Autowired
    private OutputDestination output;

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

        assertThat(output.receive(500, DAILY_QUOTE_TOPIC))
                .isNull();
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

        final var message = output.receive(500, DAILY_QUOTE_TOPIC).getPayload();
        final var payload = new String(message, UTF_8);
        final var expected  = classpathFileToString("publish/daily-quote-event.json");

        assertThat(expected)
                .isEqualTo(payload);
    }

    @SneakyThrows
    private String classpathFileToString(final String path) {
        return Files.readString(new ClassPathResource(path).getFile().toPath());
    }

}
