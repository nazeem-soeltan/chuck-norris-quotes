package dev.nazeem.chucknorrisquotes.quotes;

import java.util.List;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@Schema(description = "Response containing quotes.")
public class QuotesResponse {

    @ArraySchema(schema = @Schema(implementation = Quote.class))
    List<Quote> quotes;

}
