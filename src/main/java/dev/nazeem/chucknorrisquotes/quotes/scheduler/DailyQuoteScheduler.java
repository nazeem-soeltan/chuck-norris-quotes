package dev.nazeem.chucknorrisquotes.quotes.scheduler;

import dev.nazeem.chucknorrisquotes.quotes.QuotesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DailyQuoteScheduler {

    private final QuotesService quotesService;

    @Scheduled(
            cron = "${scheduler.daily-quote.cron:0 0 0 * * *}",
            scheduler = "dailyQuoteTaskScheduler")
    public void execute() {
        log.info("DailyQuoteScheduler started ..");

        quotesService.getOrFetchDailyQuote();

        log.info("DailyQuoteScheduler ended ..");
    }

}
