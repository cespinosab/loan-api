package com.cespinosab.loanapi.it.infrastructure.repository;

import com.cespinosab.loanapi.domain.model.PersonalLoanApplication;
import com.cespinosab.loanapi.infrastructure.repository.PersonalLoanApplicationRepository;
import com.cespinosab.loanapi.it.infrastructure.BaseIT;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.OffsetDateTime;
import java.util.Optional;

import static com.cespinosab.loanapi.domain.model.enums.PersonalLoanApplicationStatus.PENDING;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class PersonalLoanApplicationRepositoryIT extends BaseIT {

    @Autowired
    PersonalLoanApplicationRepository personalLoanApplicationRepository;

    @Test
    @Transactional
    void shouldSavePersonalLoanApplication() {
        // Given
        PersonalLoanApplication pla=  new PersonalLoanApplication("Antonio", "Ruiz", "12345678-S", 1000, "EUR");
        OffsetDateTime now = OffsetDateTime.now();
        pla.setCreatedAt(now);
        pla.setModifiedAt(now);

        // When
        personalLoanApplicationRepository.save(pla);

        // Then
        Optional<PersonalLoanApplication> result = personalLoanApplicationRepository.findById(1L);

        assertThat(result).isPresent();
        assertEquals(1L, result.get().getId());
        assertEquals("Antonio", result.get().getFirstName());
        assertEquals("Ruiz", result.get().getLastName());
        assertEquals("12345678-S", result.get().getPersonalId());
        assertEquals(1000.0, result.get().getAmount());
        assertEquals("EUR", result.get().getBadge());
        assertEquals(PENDING, result.get().getStatus());

    }
}
