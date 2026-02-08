package com.cespinosab.loanapi.domain.model.exceptions;

import com.cespinosab.loanapi.domain.exceptions.PersonalLoanApplicationNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test suite for {@link PersonalLoanApplicationNotFoundException}
 */
@ExtendWith(MockitoExtension.class)
public class PersonalLoanApplicationNotFoundExceptionTest {

    @Test
    public void constructorTest() {
        PersonalLoanApplicationNotFoundException ex = new PersonalLoanApplicationNotFoundException(1L);

        assertEquals("Personal Loan Application with id: '1' not found.", ex.getMessage());
    }
}
