package dev.nazeem.chucknorrisquotes.fixtures;

import dev.nazeem.chucknorrisquotes.client.JokesResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ChuckNorrisJokesFixtures {

    public static final JokesResponse JOKES_RESPONSE = JokesResponse.builder()
            .createdAt("2020-01-05 13:42:29.296379")
            .iconUrl("https://assets.chucknorris.host/img/avatar/chuck-norris.png")
            .id("4bvl1JLQS-uQzDK2scuXRw")
            .updatedAt("2020-01-05 13:42:29.296379")
            .url("https://api.chucknorris.io/jokes/4bvl1JLQS-uQzDK2scuXRw")
            .value("If you ever hear Chuck Norris humming \"Ain't That a Kick In the Head,\" flee for your life. You don't want to be around to see what happens next.")
            .build();

}
