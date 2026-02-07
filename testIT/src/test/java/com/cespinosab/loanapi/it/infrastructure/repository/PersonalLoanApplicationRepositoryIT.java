package com.cespinosab.loanapi.it.infrastructure.repository;

import com.cespinosab.loanapi.domain.model.PersonalLoanApplication;
import com.cespinosab.loanapi.infrastructure.repository.PersonalLoanApplicationRepository;
import com.cespinosab.loanapi.it.infrastructure.BaseIT;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import static com.cespinosab.loanapi.domain.model.enums.PersonalLoanApplicationStatus.APPROVED;
import static com.cespinosab.loanapi.domain.model.enums.PersonalLoanApplicationStatus.PENDING;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test suite for {@link PersonalLoanApplicationRepository}
 */
public class PersonalLoanApplicationRepositoryIT extends BaseIT {

    @Autowired
    PersonalLoanApplicationRepository personalLoanApplicationRepository;

    @Test
    void shouldGetAllIT() throws IOException, InterruptedException {
        // Given
        executeSql("db/existing-loans.sql");

        // When
        List<PersonalLoanApplication> result = personalLoanApplicationRepository.findAll();

        // Then
        assertEquals(2, result.size());
    }

    @Test
    void shouldGetByIdIT() throws IOException, InterruptedException {
        // Given
        executeSql("db/existing-loans.sql");

        // When
        Optional<PersonalLoanApplication> result = personalLoanApplicationRepository.findById(2L);

        // Then
        assertTrue(result.isPresent());
        assertEquals(2L, result.get().getId().longValue());
        assertEquals("Cliente2", result.get().getFirstName());
        assertEquals("Apellido2", result.get().getLastName());
        assertEquals("91234567-B", result.get().getPersonalId());
        assertEquals(3000.0, result.get().getAmount());
        assertEquals("EUR", result.get().getBadge());
        assertEquals(APPROVED, result.get().getStatus());
    }

    @Test
    @Transactional
    void shouldSaveIT() {
        // Given
        PersonalLoanApplication pla = new PersonalLoanApplication("Antonio", "Ruiz", "12345678-S", 1000, "EUR");
        OffsetDateTime now = OffsetDateTime.now();
        pla.setCreatedAt(now);
        pla.setModifiedAt(now);

        // When
        PersonalLoanApplication result = personalLoanApplicationRepository.save(pla);

        assertEquals(1L, result.getId());
        assertEquals("Antonio", result.getFirstName());
        assertEquals("Ruiz", result.getLastName());
        assertEquals("12345678-S", result.getPersonalId());
        assertEquals(1000.0, result.getAmount());
        assertEquals("EUR", result.getBadge());
        assertEquals(PENDING, result.getStatus());
    }

    @Test
    @Transactional
    void shouldUpdateIT() throws IOException, InterruptedException {
        // Given
        executeSql("db/existing-loans.sql");
        PersonalLoanApplication pla = new PersonalLoanApplication("Manuel", "Lopez", "000000-S", 20000, "EUR");
        OffsetDateTime now = OffsetDateTime.now();
        pla.setCreatedAt(now);
        pla.setModifiedAt(now);
        pla.setId(2L);
        pla.setStatus(APPROVED);

        // When
        PersonalLoanApplication result = personalLoanApplicationRepository.save(pla);

        assertEquals(2L, result.getId());
        assertEquals("Manuel", result.getFirstName());
        assertEquals("Lopez", result.getLastName());
        assertEquals("000000-S", result.getPersonalId());
        assertEquals(20000, result.getAmount());
        assertEquals("EUR", result.getBadge());
        assertEquals(APPROVED, result.getStatus());
    }
}
