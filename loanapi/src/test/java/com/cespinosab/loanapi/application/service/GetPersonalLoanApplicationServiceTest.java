package com.cespinosab.loanapi.application.service;

import com.cespinosab.loanapi.application.dto.PersonalLoanApplicationResponse;
import com.cespinosab.loanapi.application.mapper.PersonalLoanApplicationMapper;
import com.cespinosab.loanapi.domain.exceptions.PersonalLoanApplicationNotFoundException;
import com.cespinosab.loanapi.domain.model.PersonalLoanApplication;
import com.cespinosab.loanapi.infrastructure.repository.PersonalLoanApplicationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import static com.cespinosab.loanapi.domain.model.enums.PersonalLoanApplicationStatus.APPROVED;
import static com.cespinosab.loanapi.domain.model.enums.PersonalLoanApplicationStatus.PENDING;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

/**
 * Test suite for {@link GetPersonalLoanApplicationService}
 */
@ExtendWith(MockitoExtension.class)
public class GetPersonalLoanApplicationServiceTest {


    @Mock
    private PersonalLoanApplicationRepository repositoryMock;

    @Mock
    private PersonalLoanApplicationMapper mapperMock;

    @InjectMocks
    private GetPersonalLoanApplicationService getPersonalLoanApplicationService;

    @Test
    public void getAllTest() {
        // Given
        OffsetDateTime now = OffsetDateTime.now();
        PersonalLoanApplication expectedPla1 = new PersonalLoanApplication("Cliente1", "Apellido1", "12345678-A", 1000, "EUR");
        expectedPla1.setId(1L);
        expectedPla1.setStatus(APPROVED);
        expectedPla1.setCreatedAt(now);
        expectedPla1.setModifiedAt(now);
        PersonalLoanApplication expectedPla2 = new PersonalLoanApplication("Cliente2", "Apellido2", "98765432-B", 20000, "EUR");
        expectedPla2.setId(2L);
        expectedPla2.setStatus(PENDING);
        expectedPla2.setCreatedAt(now);
        expectedPla2.setModifiedAt(now);

        when(repositoryMock.findAll()).thenReturn(List.of(expectedPla1, expectedPla2));

        PersonalLoanApplicationResponse expectedResponse1 = new PersonalLoanApplicationResponse();
        expectedResponse1.setId(1L);
        expectedResponse1.setFirstName("Cliente1");
        expectedResponse1.setLastName("Apellido1");
        expectedResponse1.setPersonalId("12345678-A");
        expectedResponse1.setAmount(1000);
        expectedResponse1.setBadge("EUR");
        expectedResponse1.setStatus(PENDING);
        expectedResponse1.setCreatedAt(now);
        expectedResponse1.setModifiedAt(now);

        PersonalLoanApplicationResponse expectedResponse2 = new PersonalLoanApplicationResponse();
        expectedResponse2.setId(2L);
        expectedResponse2.setFirstName("Cliente2");
        expectedResponse2.setLastName("Apellido2");
        expectedResponse2.setPersonalId("98765432-B");
        expectedResponse2.setAmount(20000);
        expectedResponse2.setBadge("EUR");
        expectedResponse2.setStatus(APPROVED);
        expectedResponse2.setCreatedAt(now);
        expectedResponse2.setModifiedAt(now);

        when(mapperMock.mapFromDomain(any()))
                .thenReturn(expectedResponse1)
                .thenReturn(expectedResponse2);

        // When
        List<PersonalLoanApplicationResponse> result = getPersonalLoanApplicationService.getAll();

        // Then
        assertEquals(2, result.size());
        assertEquals(expectedResponse1, result.get(0));
        assertEquals(expectedResponse2, result.get(1));
    }


    @Test
    public void getByIdTest() {
        // Given
        OffsetDateTime now = OffsetDateTime.now();
        PersonalLoanApplication expectedPla = new PersonalLoanApplication("Cliente", "Apellido", "12345678-A", 1000, "EUR");
        expectedPla.setId(1L);
        expectedPla.setStatus(APPROVED);
        expectedPla.setCreatedAt(now);
        expectedPla.setModifiedAt(now);

        when(repositoryMock.findById(any())).thenReturn(Optional.of(expectedPla));

        PersonalLoanApplicationResponse expectedResponse = new PersonalLoanApplicationResponse();
        expectedResponse.setId(1L);
        expectedResponse.setFirstName("Cliente1");
        expectedResponse.setLastName("Apellido1");
        expectedResponse.setPersonalId("12345678-A");
        expectedResponse.setAmount(1000);
        expectedResponse.setBadge("EUR");
        expectedResponse.setStatus(PENDING);
        expectedResponse.setCreatedAt(now);
        expectedResponse.setModifiedAt(now);

        when(mapperMock.mapFromDomain(any())).thenReturn(expectedResponse);
        // When
        PersonalLoanApplicationResponse result = getPersonalLoanApplicationService.getById(1L);

        // Then
        assertEquals(expectedResponse, result);
    }

    @Test
    public void getByIdWhenThrowExceptionTest() {
        // Given
        when(repositoryMock.findById(any())).thenReturn(Optional.empty());

        // When
        Exception ex = assertThrows(PersonalLoanApplicationNotFoundException.class, () -> {
            getPersonalLoanApplicationService.getById(1L);
        });

        // Then
        assertEquals(PersonalLoanApplicationNotFoundException.class, ex.getClass());
        assertEquals("Personal Loan Application with id: '1' not found.", ex.getMessage());

    }
}
