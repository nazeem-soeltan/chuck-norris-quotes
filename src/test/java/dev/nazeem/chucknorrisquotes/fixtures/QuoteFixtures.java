package dev.nazeem.chucknorrisquotes.fixtures;

import static dev.nazeem.chucknorrisquotes.fixtures.ChuckNorrisJokesFixtures.JOKES_RESPONSE;

import dev.nazeem.chucknorrisquotes.quotes.Quote;
import lombok.experimental.UtilityClass;

@UtilityClass
public class QuoteFixtures {

    public static final Quote QUOTE = Quote.builder()
            .key(JOKES_RESPONSE.id())
            .text(JOKES_RESPONSE.value())
            .build();

}
