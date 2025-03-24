package dev.nazeem.chucknorrisquotes;

import dev.nazeem.chucknorrisquotes.configuration.PubSubConfiguration;
import dev.nazeem.chucknorrisquotes.configuration.TestTimeConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest
@Import({TestTimeConfiguration.class, PubSubConfiguration.class})
@ActiveProfiles({"it"})
@AutoConfigureWireMock(port = 0, stubs = "classpath:/client")
@AutoConfigureMockMvc
public @interface ChuckNorrisQuotesIT {

}
