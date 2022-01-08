package dev.nazeem.chucknorrisquotes.client;

import static dev.nazeem.chucknorrisquotes.fixtures.ChuckNorrisJokesFixtures.JOKES_RESPONSE;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import dev.nazeem.chucknorrisquotes.configuration.TestFeignConfiguration;

@ExtendWith(SpringExtension.class)
@AutoConfigureWireMock(stubs = "classpath*:/client/*.json", port = 3000)
@TestPropertySource(properties = {
        "feign.client.config.ChuckNorrisJokesClient.url = http://localhost:${wiremock.server.port}"
})
@Import({TestFeignConfiguration.class})
class ChuckNorrisJokesClientTest {

    @Autowired
    private ChuckNorrisJokesClient chuckNorrisJokesClient;

    @Test
    void shouldReturnRandomJoke() {
        final JokesResponse jokesResponse = chuckNorrisJokesClient.getRandomJoke();

        assertThat(jokesResponse)
                .isEqualTo(JOKES_RESPONSE);
    }

}