package com.cespinosab.loanapi.it.infrastructure.api;

import com.cespinosab.loanapi.application.dto.PersonalLoanApplicationRequest;
import com.cespinosab.loanapi.application.dto.PersonalLoanApplicationResponse;
import com.cespinosab.loanapi.infrastructure.api.PersonalLoanApplicationController;
import com.cespinosab.loanapi.it.infrastructure.BaseIT;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

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

    //@Test
    void shouldGetAllPersonalLoanApplication() throws IOException, InterruptedException {
        // Given
        executeSql("db/existing-loans.sql");

        // When
        ResponseEntity<List<PersonalLoanApplicationResponse>> response =
                restTemplate.exchange("/api/personalLoadApplications", HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                });

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        List<PersonalLoanApplicationResponse> body = response.getBody();

        PersonalLoanApplicationResponse pla1 = body.get(0);
        assertEquals(1L, pla1.getId());
        assertEquals("Cliente1", pla1.getFirstName());
        assertEquals("Apellido1", pla1.getLastName());
        assertEquals("12345678-A", pla1.getPersonalId());
        assertEquals(1000.0, pla1.getAmount());
        assertEquals("EUR", pla1.getBadge());
        assertEquals(PENDING, pla1.getStatus());

        PersonalLoanApplicationResponse pla2 = body.get(0);
        assertEquals(2L, pla2.getId());
        assertEquals("Cliente2", pla2.getFirstName());
        assertEquals("Apellido2", pla2.getLastName());
        assertEquals("91234567-B", pla2.getPersonalId());
        assertEquals(2000.0, pla2.getAmount());
        assertEquals("EUR", pla2.getBadge());
        assertEquals(PENDING, pla2.getStatus());
    }

    @Test
    void shouldGetPersonalLoanApplicationById() throws IOException, InterruptedException {
        // Given

        // When
        ResponseEntity<PersonalLoanApplicationResponse> response =
                restTemplate.getForEntity("/api/personalLoanApplications/1", PersonalLoanApplicationResponse.class);

        // Then
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertNotNull(response.getBody());
        PersonalLoanApplicationResponse body = response.getBody();
        assertEquals(1L, body.getId());
        assertEquals("Cliente1", body.getFirstName());
        assertEquals("Apellido1", body.getLastName());
        assertEquals("12345678-A", body.getPersonalId());
        assertEquals(1000.0, body.getAmount());
        assertEquals("EUR", body.getBadge());
        assertEquals(PENDING, body.getStatus());
    }

    @Test
    void shouldReturn404WhenGetPersonalLoanApplicationByIdNotFound() throws IOException, InterruptedException {
        // Given
        executeSql("db/existing-loans.sql");
        // When
        ResponseEntity<String> response = restTemplate.getForEntity("/api/loans/XX", String.class);
        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}


