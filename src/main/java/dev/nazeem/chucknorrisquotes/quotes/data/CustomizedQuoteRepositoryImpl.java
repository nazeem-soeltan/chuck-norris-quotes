package dev.nazeem.chucknorrisquotes.quotes.data;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Optional;

@RequiredArgsConstructor
public class CustomizedQuoteRepositoryImpl implements CustomizedQuoteRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public Optional<Quote> findQuoteForToday(final Clock clock) {
        final LocalDate today = LocalDate.now(clock);
        final Instant startOfDay = today.atStartOfDay().toInstant(ZoneOffset.UTC);
        final Instant endOfDay = today.plusDays(1).atStartOfDay().toInstant(ZoneOffset.UTC);


        final var query = new Query();
        query.addCriteria(Criteria.where("instantField")
                .gte(startOfDay)
                .lt(endOfDay));

        final Quote quote = mongoTemplate.findOne(query, Quote.class);

        return Optional.ofNullable(quote);
    }

}
