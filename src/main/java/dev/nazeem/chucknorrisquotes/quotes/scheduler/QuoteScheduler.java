package dev.nazeem.chucknorrisquotes.quotes.scheduler;

import dev.nazeem.chucknorrisquotes.quotes.QuotesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class QuoteScheduler {

    private final QuotesService quotesService;

    @Scheduled(
            cron = "${scheduler.daily-quote.cron:0 0 0 * * *}",
            scheduler = "quoteTaskScheduler")
    public void execute() {
        log.info("QuoteScheduler started ..");

        quotesService.fetchAndPublishQuote();

        log.info("QuoteScheduler ended ..");
    }

}
