package dev.nazeem.chucknorrisquotes.configuration;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients("dev.nazeem.chucknorrisquotes.client")
public class FeignConfiguration {

}
