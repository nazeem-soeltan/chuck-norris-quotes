package dev.nazeem.chucknorrisquotes.fixtures;

import static dev.nazeem.chucknorrisquotes.fixtures.ChuckNorrisJokesFixtures.JOKES_RESPONSE;

import dev.nazeem.chucknorrisquotes.quotes.data.Quote;
import lombok.experimental.UtilityClass;

@UtilityClass
public class QuoteFixtures {

    public static final Quote QUOTE = Quote.from(JOKES_RESPONSE);

}
