package dev.nazeem.chucknorrisquotes.quotes.api;

import java.util.List;
import java.util.Objects;

import dev.nazeem.chucknorrisquotes.quotes.data.Quote;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "Response containing quotes.")
public record QuotesResponse(
    @ArraySchema(schema = @Schema(implementation = Quote.class))
    List<QuoteDto> quotes
) {
    public QuotesResponse(List<QuoteDto> quotes) {
        this.quotes = Objects.requireNonNullElseGet(quotes, List::of);
    }
}
