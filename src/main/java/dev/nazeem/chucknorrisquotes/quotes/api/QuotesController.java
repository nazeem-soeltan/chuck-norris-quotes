package dev.nazeem.chucknorrisquotes.quotes.api;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import dev.nazeem.chucknorrisquotes.quotes.QuotesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = QuotesController.PATH)
@RequiredArgsConstructor
public class QuotesController {

    public static final String PATH = "/api/quotes";

    private final QuotesService quotesService;

    @Operation(
            summary = "Get Chuck Norris quotes",
            description = "Returns list of quotes.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful response."
                    )
            })
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public QuotesResponse getQuotes(
            @RequestParam(name = "max-quotes", defaultValue = "5") final int maxQuotes
    ) {
        return QuotesResponse.builder()
                .quotes(quotesService.getRandomQuotes(maxQuotes))
                .build();
    }
}
