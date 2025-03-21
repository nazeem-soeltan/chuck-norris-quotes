package dev.nazeem.chucknorrisquotes.fixtures;

import static dev.nazeem.chucknorrisquotes.fixtures.ChuckNorrisJokesFixtures.JOKES_RESPONSE;

import dev.nazeem.chucknorrisquotes.quotes.data.Quote;
import lombok.experimental.UtilityClass;

@UtilityClass
public class QuoteFixtures {

    public static final Quote QUOTE_FROM_JOKES_RESPONSE = Quote.from(JOKES_RESPONSE);

    public static final Quote DAILY_QUOTE = Quote.builder()
            .description("The real reason the Titanic sank was because Chuck Norris want to get off and go for a swim.")
            .reference("VQ1MZUz_RCK10HXr76itLA")
            .build();

}
