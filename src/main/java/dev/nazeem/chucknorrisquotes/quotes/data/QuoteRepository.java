package dev.nazeem.chucknorrisquotes.quotes.data;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuoteRepository extends MongoRepository<Quote, String>, CustomizedQuoteRepository {

}
