package dev.nazeem.chucknorrisquotes.quotes.publisher;

import dev.nazeem.chucknorrisquotes.quotes.data.Quote;
import lombok.Builder;

@Builder
public record QuoteEvent(String description) {

    public static QuoteEvent from(Quote quote) {
        return QuoteEvent.builder()
                .description(quote.getDescription())
                .build();
    }
}
