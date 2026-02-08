package com.cespinosab.loanapi.infrastructure.api;

import com.cespinosab.loanapi.domain.exceptions.PersonalLoanApplicationNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test suite for {@llink ApiExceptionHandler}
 */
@ExtendWith(MockitoExtension.class)
public class ApiExceptionHandlerTest {

    @InjectMocks
    private ApiExceptionHandler apiExceptionHandler;

    @Test
    public void handleNotFoundTest() {
        // When
        ResponseEntity<Map<String, Object>> result = apiExceptionHandler.handleNotFound(new PersonalLoanApplicationNotFoundException(3L));

        // Then
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());

        assertEquals(
                Map.of("message", "Personal Loan Application with id: '3' not found.",
                        "status", 404),
                result.getBody());
    }
}
