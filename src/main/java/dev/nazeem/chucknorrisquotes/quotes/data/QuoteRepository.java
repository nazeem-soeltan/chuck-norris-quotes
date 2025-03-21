package dev.nazeem.chucknorrisquotes.quotes.data;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface QuoteRepository extends MongoRepository<Quote, String>, CustomizedQuoteRepository {

    Optional<Quote> findQuoteForToday();

}
