package com.cespinosab.loanapi.it.infrastructure.api;

import com.cespinosab.loanapi.application.dto.PersonalLoanApplicationRequest;
import com.cespinosab.loanapi.application.dto.PersonalLoanApplicationResponse;
import com.cespinosab.loanapi.infrastructure.api.PersonalLoanApplicationController;
import com.cespinosab.loanapi.it.infrastructure.BaseIT;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static com.cespinosab.loanapi.domain.model.enums.PersonalLoanApplicationStatus.PENDING;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test suite for {@link PersonalLoanApplicationController}
 */
public class PersonalLoanApplicationControllerIT extends BaseIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Transactional
    void shouldCreatePersonalLoanApplication() {
        // Given
        PersonalLoanApplicationRequest request = new PersonalLoanApplicationRequest();
        request.setFirstName("Manuel");
        request.setLastName("Perez");
        request.setAmount(1000.0);
        request.setPersonalId("12345678-S");
        request.setBadge("EUR");

        // When
        ResponseEntity<PersonalLoanApplicationResponse> response = restTemplate.postForEntity("/api/personalLoadApplications", request, PersonalLoanApplicationResponse.class);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        PersonalLoanApplicationResponse body = response.getBody();
        assertEquals(1L, body.getId());
        assertEquals("Manuel", body.getFirstName());
        assertEquals("Perez", body.getLastName());
        assertEquals("12345678-S", body.getPersonalId());
        assertEquals(1000.0, body.getAmount());
        assertEquals("EUR", body.getBadge());
        assertEquals(PENDING, body.getStatus());
    }

    @Test
    void shouldGetAllPersonalLoanApplication() throws IOException, InterruptedException {
        // Given
        executeSql("db/existing-loans.sql");

        // When
        ResponseEntity<PersonalLoanApplicationResponse> getResponse = restTemplate.getForEntity("/api/personalLoadApplications/1", PersonalLoanApplicationResponse.class);
        // Then
        assertNotNull(getResponse.getBody());
        assertThat(getResponse.getBody().getAmount()).isEqualTo(1000.0);
    }
}


