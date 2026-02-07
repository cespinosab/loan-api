package com.cespinosab.loanapi.it.infrastructure;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.MountableFile;

import java.io.IOException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public abstract class BaseIT {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine")
            .withDatabaseName("loans")
            .withUsername("user")
            .withPassword("mypassword")
            .withInitScript("db/init-db.sql");


    @BeforeAll
    static void beforeAll() throws IOException, InterruptedException {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @BeforeEach
    void resetDB() throws IOException, InterruptedException {
        postgres.execInContainer(
                "psql", "-U", postgres.getUsername(), "-d", postgres.getDatabaseName(),
                "-c", "TRUNCATE TABLE loans.personal_loan_application RESTART IDENTITY;");
    }

    public void executeSql(String sqlFile) throws IOException, InterruptedException {
        postgres.copyFileToContainer(MountableFile.forClasspathResource(sqlFile), "/tmp/" +sqlFile);
        postgres.execInContainer(
                "psql", "-U", postgres.getUsername(), "-d", postgres.getDatabaseName(), "-f", "/tmp/"+sqlFile);
    }

}



