package dev.nazeem.chucknorrisquotes.fixtures;

import static dev.nazeem.chucknorrisquotes.fixtures.ChuckNorrisJokesFixtures.JOKES_RESPONSE;

import dev.nazeem.chucknorrisquotes.quotes.Quote;
import lombok.experimental.UtilityClass;

@UtilityClass
public class QuoteFixtures {

    public static final Quote QUOTE = Quote.builder()
            .key(JOKES_RESPONSE.getId())
            .text(JOKES_RESPONSE.getValue())
            .build();

}
