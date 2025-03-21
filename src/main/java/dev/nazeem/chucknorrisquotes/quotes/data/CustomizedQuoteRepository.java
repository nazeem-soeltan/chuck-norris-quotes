package dev.nazeem.chucknorrisquotes.quotes.data;

import java.time.Clock;
import java.util.Optional;

public interface CustomizedQuoteRepository {

    Optional<Quote> findQuoteForToday(Clock clock);

}
