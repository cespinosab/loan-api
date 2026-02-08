package com.cespinosab.loanapi.domain.model.enums;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.cespinosab.loanapi.domain.model.enums.PersonalLoanApplicationStatus.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Test suite for {@link PersonalLoanApplicationStatus}
 */
@ExtendWith(MockitoExtension.class)
public class PersonalLoanApplicationStatusTest {

    @Test
    public void parseValueWhenCamelCaseTest() {
        assertEquals(PENDING, PersonalLoanApplicationStatus.parseValue("Pendiente"));
    }

    @Test
    public void parseValueWhenMayusTest() {
        assertEquals(APPROVED, PersonalLoanApplicationStatus.parseValue("APROBADA"));
    }

    @Test
    public void parseValueWhenLowerTest() {
        assertEquals(REJECTED, PersonalLoanApplicationStatus.parseValue("rechazada"));
    }

    @Test
    public void parseValueWhenNotValidTest() {
        assertNull(PersonalLoanApplicationStatus.parseValue("EN ESTUDIO"));
    }
}
