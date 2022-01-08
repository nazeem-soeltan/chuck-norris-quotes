package dev.nazeem.chucknorrisquotes.quotes;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@Schema(description = "A Quote uniquely identified by a key.")
public class Quote {

    String key;

    String text;

}
