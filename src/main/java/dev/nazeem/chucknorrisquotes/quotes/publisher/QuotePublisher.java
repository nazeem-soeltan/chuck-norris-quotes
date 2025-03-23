package dev.nazeem.chucknorrisquotes.quotes.publisher;

import dev.nazeem.chucknorrisquotes.quotes.data.Quote;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class QuotePublisher {

    private final StreamBridge streamBridge;

    public void publishQuote(final Quote quote) {
        final var quoteEvent = QuoteEvent.from(quote);
        log.info("Publishing quote: {}", quoteEvent);
        streamBridge.send("quote", quoteEvent);
    }

    public void publishDailyQuote(final Quote quote) {
        final var dailyQuoteEvent = DailyQuoteEvent.from(quote);
        log.info("Publishing daily quote: {}", quote);
        streamBridge.send("daily-quote", dailyQuoteEvent);
    }
}
