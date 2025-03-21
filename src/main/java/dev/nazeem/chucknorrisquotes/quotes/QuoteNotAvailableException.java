package dev.nazeem.chucknorrisquotes.quotes;

public class QuoteNotAvailableException extends RuntimeException {

    public QuoteNotAvailableException() {
        super("Quote not available");
    }

}
