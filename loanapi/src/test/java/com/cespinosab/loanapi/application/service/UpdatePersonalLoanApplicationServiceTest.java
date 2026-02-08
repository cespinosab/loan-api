package com.cespinosab.loanapi.application.service;

import com.cespinosab.loanapi.application.dto.PersonalLoanApplicationRequest;
import com.cespinosab.loanapi.application.dto.PersonalLoanApplicationResponse;
import com.cespinosab.loanapi.application.mapper.PersonalLoanApplicationMapper;
import com.cespinosab.loanapi.domain.model.PersonalLoanApplication;
import com.cespinosab.loanapi.domain.model.enums.PersonalLoanApplicationStatus;
import com.cespinosab.loanapi.infrastructure.repository.PersonalLoanApplicationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.Optional;

import static com.cespinosab.loanapi.domain.model.enums.PersonalLoanApplicationStatus.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Test suite for {@link UpdatePersonalLoanApplicationService}
 */
@ExtendWith(MockitoExtension.class)
public class UpdatePersonalLoanApplicationServiceTest {

    @Mock
    private PersonalLoanApplicationRepository repositoryMock;

    @Mock
    private PersonalLoanApplicationMapper mapperMock;

    @InjectMocks
    @Spy
    private UpdatePersonalLoanApplicationService updatePersonalLoanApplicationService;

    @Test
    public void updateTest() {
        // Given
        OffsetDateTime now = OffsetDateTime.now();

        PersonalLoanApplication requestPlaToUpdateMock = new PersonalLoanApplication("Cliente", "Apellido", "12345678-A", 1000, "EUR");
        requestPlaToUpdateMock.setAmount(5000);
        requestPlaToUpdateMock.setStatus(APPROVED);
        when(mapperMock.mapToDomain(any())).thenReturn(requestPlaToUpdateMock);

        PersonalLoanApplication existingPlaMock = new PersonalLoanApplication("Cliente", "Apellido", "12345678-A", 1000, "EUR");
        existingPlaMock.setId(1L);
        existingPlaMock.setStatus(PENDING);
        existingPlaMock.setModifiedAt(now.minusDays(1));
        existingPlaMock.setModifiedAt(now.minusDays(1));
        when(repositoryMock.findById(any())).thenReturn(Optional.of(existingPlaMock));

        doReturn(true).when(updatePersonalLoanApplicationService).isValidStatusChange(any(PersonalLoanApplicationStatus.class),any(PersonalLoanApplicationStatus.class));

        PersonalLoanApplication updatedPlaMock = new PersonalLoanApplication("Cliente", "Apellido", "12345678-A", 5000, "EUR");
        existingPlaMock.setId(1L);
        existingPlaMock.setStatus(APPROVED);
        existingPlaMock.setModifiedAt(now);
        when(repositoryMock.save(any())).thenReturn(updatedPlaMock);

        PersonalLoanApplicationResponse responseMock = new PersonalLoanApplicationResponse();
        responseMock.setId(1L);
        responseMock.setFirstName("Cliente");
        responseMock.setLastName("Apellido");
        responseMock.setPersonalId("12345678-A");
        responseMock.setAmount(5000);
        responseMock.setBadge("EUR");
        responseMock.setStatus(APPROVED);
        responseMock.setCreatedAt(now);
        responseMock.setModifiedAt(now);
        when(mapperMock.mapFromDomain(any())).thenReturn(responseMock);

        // When
        PersonalLoanApplicationRequest request = new PersonalLoanApplicationRequest();
        request.setFirstName("Cliente");
        request.setLastName("Apellido");
        request.setPersonalId("12345678-A");
        request.setAmount(5000);
        request.setBadge("EUR");
        request.setStatus(APPROVED);

        PersonalLoanApplicationResponse result = updatePersonalLoanApplicationService.update(1L, request);

        // Then
        assertEquals(responseMock, result);
        verify(mapperMock).mapToDomain(any());
        verify(repositoryMock).findById(1L);
        verify(updatePersonalLoanApplicationService).isValidStatusChange(any(PersonalLoanApplicationStatus.class), any(PersonalLoanApplicationStatus.class));
        verify(repositoryMock).save(any());
        verify(mapperMock).mapFromDomain(any());
        verifyNoMoreInteractions(mapperMock, repositoryMock);
    }


    @Test
    public void updateWhenStatusIsNotValidTest() {
        // Given
        OffsetDateTime now = OffsetDateTime.now();

        PersonalLoanApplication requestPlaToUpdateMock = new PersonalLoanApplication("Cliente", "Apellido", "12345678-A", 1000, "EUR");
        requestPlaToUpdateMock.setAmount(5000);
        requestPlaToUpdateMock.setStatus(APPROVED);
        when(mapperMock.mapToDomain(any())).thenReturn(requestPlaToUpdateMock);

        PersonalLoanApplication existingPlaMock = new PersonalLoanApplication("Cliente", "Apellido", "12345678-A", 1000, "EUR");
        existingPlaMock.setId(1L);
        existingPlaMock.setStatus(CANCELLED);
        existingPlaMock.setModifiedAt(now.minusDays(1));
        existingPlaMock.setModifiedAt(now.minusDays(1));
        when(repositoryMock.findById(any())).thenReturn(Optional.of(existingPlaMock));

        doReturn(false).when(updatePersonalLoanApplicationService).isValidStatusChange(any(PersonalLoanApplicationStatus.class),any(PersonalLoanApplicationStatus.class));

        PersonalLoanApplication updatedPlaMock = new PersonalLoanApplication("Cliente", "Apellido", "12345678-A", 5000, "EUR");
        existingPlaMock.setId(1L);
        existingPlaMock.setStatus(CANCELLED);
        existingPlaMock.setModifiedAt(now);
        when(repositoryMock.save(any())).thenReturn(updatedPlaMock);

        PersonalLoanApplicationResponse responseMock = new PersonalLoanApplicationResponse();
        responseMock.setId(1L);
        responseMock.setFirstName("Cliente");
        responseMock.setLastName("Apellido");
        responseMock.setPersonalId("12345678-A");
        responseMock.setAmount(5000);
        responseMock.setBadge("EUR");
        responseMock.setStatus(CANCELLED);
        responseMock.setCreatedAt(now);
        responseMock.setModifiedAt(now);
        when(mapperMock.mapFromDomain(any())).thenReturn(responseMock);

        // When
        PersonalLoanApplicationRequest request = new PersonalLoanApplicationRequest();
        request.setFirstName("Cliente");
        request.setLastName("Apellido");
        request.setPersonalId("12345678-A");
        request.setAmount(5000);
        request.setBadge("EUR");
        request.setStatus(APPROVED);

        PersonalLoanApplicationResponse result = updatePersonalLoanApplicationService.update(1L, request);

        // Then
        assertEquals(responseMock, result);

        verify(mapperMock).mapToDomain(any());
        verify(repositoryMock).findById(1L);
        verify(updatePersonalLoanApplicationService).isValidStatusChange(any(PersonalLoanApplicationStatus.class), any(PersonalLoanApplicationStatus.class));
        verify(repositoryMock).save(any());
        verify(mapperMock).mapFromDomain(any());
        verifyNoMoreInteractions(mapperMock, repositoryMock);
    }

    @Test
    public void updateWhenNotExistTest() {
        // Given
        OffsetDateTime now = OffsetDateTime.now();

        PersonalLoanApplication requestPlaToUpdateMock = new PersonalLoanApplication("Cliente", "Apellido", "12345678-A", 1000, "EUR");
        requestPlaToUpdateMock.setAmount(5000);
        requestPlaToUpdateMock.setStatus(PENDING);
        when(mapperMock.mapToDomain(any())).thenReturn(requestPlaToUpdateMock);

        when(repositoryMock.findById(any())).thenReturn(Optional.empty());

        PersonalLoanApplicationResponse responseMock = new PersonalLoanApplicationResponse();
        responseMock.setId(1L);
        responseMock.setFirstName("Cliente");
        responseMock.setLastName("Apellido");
        responseMock.setPersonalId("12345678-A");
        responseMock.setAmount(5000);
        responseMock.setBadge("EUR");
        responseMock.setStatus(PENDING);
        responseMock.setCreatedAt(now);
        responseMock.setModifiedAt(now);
        when(mapperMock.mapFromDomain(any())).thenReturn(responseMock);

        // When
        PersonalLoanApplicationRequest request = new PersonalLoanApplicationRequest();
        request.setFirstName("Cliente");
        request.setLastName("Apellido");
        request.setPersonalId("12345678-A");
        request.setAmount(5000);
        request.setBadge("EUR");
        request.setStatus(PENDING);

        PersonalLoanApplicationResponse result = updatePersonalLoanApplicationService.update(1L, request);

        // Then
        assertEquals(responseMock, result);
        verify(mapperMock).mapToDomain(any());
        verify(repositoryMock).findById(1L);
        verify(repositoryMock).save(any());
        verify(mapperMock).mapFromDomain(any());
        verifyNoMoreInteractions(mapperMock, repositoryMock);
    }

    @Test
    public void isValidStatusChangeValidPendingApprovedTest() {
        assertTrue(updatePersonalLoanApplicationService.isValidStatusChange(PENDING, APPROVED));
    }

    @Test
    public void isValidStatusChangeValidPendingRejectedTest() {
        assertTrue(updatePersonalLoanApplicationService.isValidStatusChange(PENDING, REJECTED));
    }

    @Test
    public void isValidStatusChangeNotValidPendingCancelledTest() {
        assertFalse(updatePersonalLoanApplicationService.isValidStatusChange(PENDING, CANCELLED));
    }

    @Test
    public void isValidStatusChangeValidApprovedCancelledTest() {
        assertTrue(updatePersonalLoanApplicationService.isValidStatusChange(APPROVED, CANCELLED));
    }

    @Test
    public void isValidStatusChangeNotValidApprovedPendingTest() {
        assertFalse(updatePersonalLoanApplicationService.isValidStatusChange(APPROVED, PENDING));
    }

    @Test
    public void isValidStatusChangeNotValidStatusTest() {
        assertFalse(updatePersonalLoanApplicationService.isValidStatusChange(APPROVED, null));
    }

}
