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
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;

import static com.cespinosab.loanapi.domain.model.enums.PersonalLoanApplicationStatus.PENDING;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Test suite for {@link CreatePersonalLoanApplicationService}
 */
@ExtendWith(MockitoExtension.class)
public class CreatePersonalLoanApplicationServiceTest {

    @Mock
    private PersonalLoanApplicationRepository repositoryMock;

    @Mock
    private PersonalLoanApplicationMapper mapperMock;

    @InjectMocks
    private CreatePersonalLoanApplicationService createPersonalLoanApplicationService;

    @Test
    public void createTest() {
        // Given
        OffsetDateTime now = OffsetDateTime.now();
        PersonalLoanApplication mockPla = new PersonalLoanApplication("Cliente", "Apellido", "12345678-A", 1000, "EUR");;
        when(mapperMock.mapToDomain(any())).thenReturn(mockPla);

        mockPla.setId(1L);
        when(repositoryMock.save(any())).thenReturn(mockPla);

        PersonalLoanApplicationResponse mockResponse = new PersonalLoanApplicationResponse();
        mockResponse.setId(1L);
        mockResponse.setFirstName("Cliente");
        mockResponse.setLastName("Apellido");
        mockResponse.setPersonalId("12345678-A");
        mockResponse.setAmount(1000);
        mockResponse.setBadge("EUR");
        mockResponse.setStatus(PENDING);
        mockResponse.setCreatedAt(now);
        mockResponse.setModifiedAt(now);

        when(mapperMock.mapFromDomain(any())).thenReturn(mockResponse);

        // When
        PersonalLoanApplicationRequest request = new PersonalLoanApplicationRequest();
        request.setFirstName("Cliente");
        request.setLastName("Apellido");
        request.setPersonalId("12345678-A");
        request.setAmount(1000);
        request.setBadge("EUR");

        PersonalLoanApplicationResponse result = createPersonalLoanApplicationService.create(request);

        // Then
        assertEquals(mockResponse, result);

        verify(mapperMock).mapToDomain(any());
        verify(repositoryMock).save(any());
        verify(mapperMock).mapFromDomain(any());
        verifyNoMoreInteractions(mapperMock, repositoryMock);
    }

}
