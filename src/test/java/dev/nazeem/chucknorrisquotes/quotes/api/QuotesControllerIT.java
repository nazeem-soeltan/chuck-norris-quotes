package dev.nazeem.chucknorrisquotes.quotes.api;

import dev.nazeem.chucknorrisquotes.ChuckNorrisQuotesIT;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.assertj.MvcTestResult;

import static dev.nazeem.chucknorrisquotes.quotes.api.QuotesController.PATH;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@ChuckNorrisQuotesIT
public class QuotesControllerIT {

    static final String RESPONSE_PATH = "api/quotes/%s";

    @Autowired
    private MockMvcTester mockMvc;

    @Test
    void returnFiveQuotesWhenMaxQuotesIsOmitted() {
        final MvcTestResult response = mockMvc.get()
                .uri(PATH)
                .exchange();

        assertThat(response)
                .hasStatusOk()
                .hasContentTypeCompatibleWith(APPLICATION_JSON_VALUE)
                .bodyJson()
                .isStrictlyEqualTo(RESPONSE_PATH.formatted("getQuotes-no-limit-default-5.json"));
    }

    @Test
    void returnTwoQuotesWhenLimitIs2() {
        final MvcTestResult response = mockMvc.get()
                .uri(PATH)
                .param("max-quotes", "2")
                .exchange();

        assertThat(response)
                .hasStatusOk()
                .hasContentTypeCompatibleWith(APPLICATION_JSON_VALUE)
                .bodyJson()
                .isStrictlyEqualTo(RESPONSE_PATH.formatted("getQuotes-limit-2.json"));
    }

}
