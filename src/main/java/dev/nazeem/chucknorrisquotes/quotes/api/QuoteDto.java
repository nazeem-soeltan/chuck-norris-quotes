package dev.nazeem.chucknorrisquotes.quotes.api;

import dev.nazeem.chucknorrisquotes.quotes.data.Quote;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "A Quote.")
public record QuoteDto(String description)
{
    public static QuoteDto from(Quote quote) {
        return new QuoteDto(quote.getDescription());
    }
}
