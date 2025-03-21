package dev.nazeem.chucknorrisquotes;

import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@ChuckNorrisQuotesIT
public class AbstractBaseIT {

    @ServiceConnection
    final static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.16");

}
