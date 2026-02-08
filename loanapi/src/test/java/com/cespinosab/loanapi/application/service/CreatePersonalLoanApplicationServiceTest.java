package com.cespinosab.loanapi.application.service;

import com.cespinosab.loanapi.application.dto.PersonalLoanApplicationRequest;
import com.cespinosab.loanapi.application.dto.PersonalLoanApplicationResponse;
import com.cespinosab.loanapi.application.mapper.PersonalLoanApplicationMapper;
import com.cespinosab.loanapi.domain.model.PersonalLoanApplication;
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
import static org.mockito.Mockito.when;

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
        PersonalLoanApplication expectedPla = new PersonalLoanApplication("Cliente", "Apellido", "12345678-A", 1000, "EUR");;
        when(mapperMock.mapToDomain(any())).thenReturn(expectedPla);

        expectedPla.setId(1L);
        when(repositoryMock.save(any())).thenReturn(expectedPla);

        PersonalLoanApplicationResponse expectedResponse = new PersonalLoanApplicationResponse();
        expectedResponse.setId(1L);
        expectedResponse.setFirstName("Cliente");
        expectedResponse.setLastName("Apellido");
        expectedResponse.setPersonalId("12345678-A");
        expectedResponse.setAmount(1000);
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
        request.setAmount(1000);
        request.setBadge("EUR");

        PersonalLoanApplicationResponse result = createPersonalLoanApplicationService.create(request);

        // Then
        assertEquals(expectedResponse, result);
    }

}
