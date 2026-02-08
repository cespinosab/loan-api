package com.cespinosab.loanapi.application.mapper;

import com.cespinosab.loanapi.application.dto.PersonalLoanApplicationRequest;
import com.cespinosab.loanapi.application.dto.PersonalLoanApplicationResponse;
import com.cespinosab.loanapi.domain.model.PersonalLoanApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.OffsetDateTime;

import static com.cespinosab.loanapi.domain.model.enums.PersonalLoanApplicationStatus.PENDING;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doReturn;

/**
 * Test suite for {@link PersonalLoanApplicationMapper}
 */
@ExtendWith(MockitoExtension.class)
public class PersonalLoanApplicationMapperTest {

    @Spy
    private ModelMapper modelMapperSpy = new ModelMapper();

    @InjectMocks
    private PersonalLoanApplicationMapper personalLoanApplicationMapper;

    @Test
    public void mapFromDomainTest() {
        // Given
        OffsetDateTime now = OffsetDateTime.now();

        doCallRealMethod().when(modelMapperSpy).typeMap(any(), any());

        PersonalLoanApplicationResponse responseMock = new PersonalLoanApplicationResponse();
        responseMock.setId(1L);
        responseMock.setFirstName("Cliente");
        responseMock.setLastName("Apellido");
        responseMock.setPersonalId("12345678-A");
        responseMock.setAmount(1000);
        responseMock.setBadge("EUR");
        responseMock.setStatus(PENDING);
        responseMock.setCreatedAt(now);
        responseMock.setModifiedAt(now);

        doReturn(responseMock).when(modelMapperSpy).map(any(), any());

        // When
        PersonalLoanApplication pla = new PersonalLoanApplication("Cliente", "Apellido", "12345678-A", 1000, "EUR");
        pla.setId(1L);
        pla.setStatus(PENDING);
        pla.setCreatedAt(now);
        pla.setModifiedAt(now);

        PersonalLoanApplicationResponse result = personalLoanApplicationMapper.mapFromDomain(pla);

        // Then
        assertEquals(responseMock, result);
    }


    @Test
    public void mapToDomainTest() {
        // Given
        doCallRealMethod().when(modelMapperSpy).typeMap(any(), any());

        PersonalLoanApplication domainMock = new PersonalLoanApplication("Cliente", "Apellido", "12345678-A", 1000, "EUR");
        doReturn(domainMock).when(modelMapperSpy).map(any(), any());

        // When
        PersonalLoanApplicationRequest request = new PersonalLoanApplicationRequest();
        request.setFirstName("Cliente");
        request.setLastName("Apellido");
        request.setPersonalId("12345678-A");
        request.setAmount(1000);
        request.setBadge("EUR");

        PersonalLoanApplication result = personalLoanApplicationMapper.mapToDomain(request);

        // Then
        assertEquals(domainMock, result);
    }
}
