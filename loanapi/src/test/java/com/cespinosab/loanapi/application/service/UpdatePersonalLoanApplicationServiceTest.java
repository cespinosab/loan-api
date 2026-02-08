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

        PersonalLoanApplication requestPlaToUpdate = new PersonalLoanApplication("Cliente", "Apellido", "12345678-A", 1000, "EUR");
        requestPlaToUpdate.setAmount(5000);
        requestPlaToUpdate.setStatus(APPROVED);
        when(mapperMock.mapToDomain(any())).thenReturn(requestPlaToUpdate);

        PersonalLoanApplication existingPla = new PersonalLoanApplication("Cliente", "Apellido", "12345678-A", 1000, "EUR");
        existingPla.setId(1L);
        existingPla.setStatus(PENDING);
        existingPla.setModifiedAt(now.minusDays(1));
        existingPla.setModifiedAt(now.minusDays(1));
        when(repositoryMock.findById(any())).thenReturn(Optional.of(existingPla));

        doReturn(true).when(updatePersonalLoanApplicationService).isValidStatusChange(any(PersonalLoanApplicationStatus.class),any(PersonalLoanApplicationStatus.class));

        PersonalLoanApplication expectedUpdatedPla = new PersonalLoanApplication("Cliente", "Apellido", "12345678-A", 5000, "EUR");
        existingPla.setId(1L);
        existingPla.setStatus(APPROVED);
        existingPla.setModifiedAt(now);
        when(repositoryMock.save(any())).thenReturn(expectedUpdatedPla);

        PersonalLoanApplicationResponse expectedResponse = new PersonalLoanApplicationResponse();
        expectedResponse.setId(1L);
        expectedResponse.setFirstName("Cliente");
        expectedResponse.setLastName("Apellido");
        expectedResponse.setPersonalId("12345678-A");
        expectedResponse.setAmount(5000);
        expectedResponse.setBadge("EUR");
        expectedResponse.setStatus(APPROVED);
        expectedResponse.setCreatedAt(now);
        expectedResponse.setModifiedAt(now);
        when(mapperMock.mapFromDomain(any())).thenReturn(expectedResponse);

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
        assertEquals(expectedResponse, result);

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

        PersonalLoanApplication requestPlaToUpdate = new PersonalLoanApplication("Cliente", "Apellido", "12345678-A", 1000, "EUR");
        requestPlaToUpdate.setAmount(5000);
        requestPlaToUpdate.setStatus(APPROVED);
        when(mapperMock.mapToDomain(any())).thenReturn(requestPlaToUpdate);

        PersonalLoanApplication existingPla = new PersonalLoanApplication("Cliente", "Apellido", "12345678-A", 1000, "EUR");
        existingPla.setId(1L);
        existingPla.setStatus(CANCELLED);
        existingPla.setModifiedAt(now.minusDays(1));
        existingPla.setModifiedAt(now.minusDays(1));
        when(repositoryMock.findById(any())).thenReturn(Optional.of(existingPla));

        doReturn(false).when(updatePersonalLoanApplicationService).isValidStatusChange(any(PersonalLoanApplicationStatus.class),any(PersonalLoanApplicationStatus.class));

        PersonalLoanApplication expectedUpdatedPla = new PersonalLoanApplication("Cliente", "Apellido", "12345678-A", 5000, "EUR");
        existingPla.setId(1L);
        existingPla.setStatus(CANCELLED);
        existingPla.setModifiedAt(now);
        when(repositoryMock.save(any())).thenReturn(expectedUpdatedPla);

        PersonalLoanApplicationResponse expectedResponse = new PersonalLoanApplicationResponse();
        expectedResponse.setId(1L);
        expectedResponse.setFirstName("Cliente");
        expectedResponse.setLastName("Apellido");
        expectedResponse.setPersonalId("12345678-A");
        expectedResponse.setAmount(5000);
        expectedResponse.setBadge("EUR");
        expectedResponse.setStatus(CANCELLED);
        expectedResponse.setCreatedAt(now);
        expectedResponse.setModifiedAt(now);
        when(mapperMock.mapFromDomain(any())).thenReturn(expectedResponse);

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
        assertEquals(expectedResponse, result);

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

        PersonalLoanApplication requestPlaToUpdate = new PersonalLoanApplication("Cliente", "Apellido", "12345678-A", 1000, "EUR");
        requestPlaToUpdate.setAmount(5000);
        requestPlaToUpdate.setStatus(PENDING);
        when(mapperMock.mapToDomain(any())).thenReturn(requestPlaToUpdate);

        when(repositoryMock.findById(any())).thenReturn(Optional.empty());

        PersonalLoanApplicationResponse expectedResponse = new PersonalLoanApplicationResponse();
        expectedResponse.setId(1L);
        expectedResponse.setFirstName("Cliente");
        expectedResponse.setLastName("Apellido");
        expectedResponse.setPersonalId("12345678-A");
        expectedResponse.setAmount(5000);
        expectedResponse.setBadge("EUR");
        expectedResponse.setStatus(PENDING);
        expectedResponse.setCreatedAt(now);
        expectedResponse.setModifiedAt(now);
        when(mapperMock.mapFromDomain(any())).thenReturn(expectedResponse);

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
        assertEquals(expectedResponse, result);
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
