package dev.nazeem.chucknorrisquotes.quotes;

import static dev.nazeem.chucknorrisquotes.fixtures.QuoteFixtures.QUOTE;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(QuotesController.class)
class QuotesControllerTest {

    @MockBean
    private QuotesService quotesService;

    @Autowired
    private MockMvc mvc;

    @Test
    void shouldReturnFiveQuotesWhenMaxQuotesIsOmitted() throws Exception {
        when(quotesService.getRandomQuotes(5))
                .thenReturn(List.of(QUOTE, QUOTE, QUOTE, QUOTE, QUOTE));

        mvc.perform(
                get("/api/quotes").accept(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.quotes").isNotEmpty())
                .andExpect(jsonPath("$.quotes", hasSize(5)));
    }

    @Test
    void shouldReturnQuote() throws Exception {
        when(quotesService.getRandomQuotes(1))
                .thenReturn(List.of(QUOTE));

        mvc.perform(
                get("/api/quotes?max-quotes=1").accept(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.quotes").isNotEmpty())
                .andExpect(jsonPath("$.quotes[0].key").value(QUOTE.key()))
                .andExpect(jsonPath("$.quotes[0].text").value(QUOTE.text()));
    }

}