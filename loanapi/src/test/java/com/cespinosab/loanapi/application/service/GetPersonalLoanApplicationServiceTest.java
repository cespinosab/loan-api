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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
        PersonalLoanApplication plaMock1 = new PersonalLoanApplication("Cliente1", "Apellido1", "12345678-A", 1000, "EUR");
        plaMock1.setId(1L);
        plaMock1.setStatus(APPROVED);
        plaMock1.setCreatedAt(now);
        plaMock1.setModifiedAt(now);
        PersonalLoanApplication plaMock2 = new PersonalLoanApplication("Cliente2", "Apellido2", "98765432-B", 20000, "EUR");
        plaMock2.setId(2L);
        plaMock2.setStatus(PENDING);
        plaMock2.setCreatedAt(now);
        plaMock2.setModifiedAt(now);

        when(repositoryMock.findAll()).thenReturn(List.of(plaMock1, plaMock2));

        PersonalLoanApplicationResponse responseMock1 = new PersonalLoanApplicationResponse();
        responseMock1.setId(1L);
        responseMock1.setFirstName("Cliente1");
        responseMock1.setLastName("Apellido1");
        responseMock1.setPersonalId("12345678-A");
        responseMock1.setAmount(1000);
        responseMock1.setBadge("EUR");
        responseMock1.setStatus(PENDING);
        responseMock1.setCreatedAt(now);
        responseMock1.setModifiedAt(now);

        PersonalLoanApplicationResponse responseMock2 = new PersonalLoanApplicationResponse();
        responseMock2.setId(2L);
        responseMock2.setFirstName("Cliente2");
        responseMock2.setLastName("Apellido2");
        responseMock2.setPersonalId("98765432-B");
        responseMock2.setAmount(20000);
        responseMock2.setBadge("EUR");
        responseMock2.setStatus(APPROVED);
        responseMock2.setCreatedAt(now);
        responseMock2.setModifiedAt(now);

        when(mapperMock.mapFromDomain(any()))
                .thenReturn(responseMock1)
                .thenReturn(responseMock2);

        // When
        List<PersonalLoanApplicationResponse> result = getPersonalLoanApplicationService.getAll();

        // Then
        assertEquals(2, result.size());
        assertEquals(responseMock1, result.get(0));
        assertEquals(responseMock2, result.get(1));

        verify(repositoryMock).findAll();
        verify(mapperMock, times(2)).mapFromDomain(any());
        verifyNoMoreInteractions(mapperMock, repositoryMock);
    }


    @Test
    public void getByIdTest() {
        // Given
        OffsetDateTime now = OffsetDateTime.now();
        PersonalLoanApplication plaMock = new PersonalLoanApplication("Cliente", "Apellido", "12345678-A", 1000, "EUR");
        plaMock.setId(1L);
        plaMock.setStatus(APPROVED);
        plaMock.setCreatedAt(now);
        plaMock.setModifiedAt(now);
        when(repositoryMock.findById(any())).thenReturn(Optional.of(plaMock));

        PersonalLoanApplicationResponse responseMock = new PersonalLoanApplicationResponse();
        responseMock.setId(1L);
        responseMock.setFirstName("Cliente1");
        responseMock.setLastName("Apellido1");
        responseMock.setPersonalId("12345678-A");
        responseMock.setAmount(1000);
        responseMock.setBadge("EUR");
        responseMock.setStatus(PENDING);
        responseMock.setCreatedAt(now);
        responseMock.setModifiedAt(now);

        when(mapperMock.mapFromDomain(any())).thenReturn(responseMock);
        // When
        PersonalLoanApplicationResponse result = getPersonalLoanApplicationService.getById(1L);

        // Then
        assertEquals(responseMock, result);
        verify(repositoryMock).findById(1L);
        verify(mapperMock).mapFromDomain(any());
        verifyNoMoreInteractions(mapperMock, repositoryMock);
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
