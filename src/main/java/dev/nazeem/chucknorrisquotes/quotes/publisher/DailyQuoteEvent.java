package dev.nazeem.chucknorrisquotes.quotes.publisher;

import dev.nazeem.chucknorrisquotes.quotes.data.Quote;
import lombok.Builder;

import java.time.Instant;

@Builder
public record DailyQuoteEvent(String description, Instant createdAt) {

    public static DailyQuoteEvent from(Quote quote) {
        return DailyQuoteEvent.builder()
                .description(quote.description)
                .createdAt(quote.createdAt)
                .build();
    }
}
