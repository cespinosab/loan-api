package com.cespinosab.loanapi.infrastructure.api;

import com.cespinosab.loanapi.domain.model.PersonalLoanApplication;
import com.cespinosab.loanapi.infrastructure.repository.PersonalLoanApplicationRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonalLoanApplicationControllerTest {

    @Autowired
    PersonalLoanApplicationRepository personalLoanApplicationRepository;

    @LocalServerPort
    private Integer port;

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer("postgres:16-alpine")
            .withDatabaseName("loans")
            .withUsername("user")
            .withPassword("password");

    @BeforeAll
    static void beforeAll() {
        postgres.start();

    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @BeforeEach
    void setUp() throws IOException, InterruptedException {
        RestAssured.baseURI = "http://localhost:" + port;
        postgres.execInContainer("CREATE SCHEMA loans;");
        personalLoanApplicationRepository.deleteAll();
    }

    @Test
    void shouldGetAllPersonalLoanApplications() {
        List<PersonalLoanApplication> customers = List.of(
                new PersonalLoanApplication("Antonio", "Ruiz", "12345678-S", 1000, "EUR")
                //new PersonalLoanApplication(null, "Dennis", "dennis@mail.com")
        );
        personalLoanApplicationRepository.saveAll(customers);

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/v1/personaApplicationLoans")
                .then()
                .statusCode(200)
                .body(".", hasSize(2));
    }
}
