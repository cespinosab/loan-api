package com.cespinosab.loanapi.application.mapper;

import com.cespinosab.loanapi.application.dto.PersonalLoanApplicationRequest;
import com.cespinosab.loanapi.application.dto.PersonalLoanApplicationResponse;
import com.cespinosab.loanapi.domain.model.PersonalLoanApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.util.ReflectionTestUtils;

import static com.cespinosab.loanapi.domain.model.enums.PersonalLoanApplicationStatus.PENDING;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

/**
 * Test suite for {@link PersonalLoanApplicationMapper}
 */
@ExtendWith(MockitoExtension.class)
public class PersonalLoanApplicationMapperTest {

    @Mock
    private ModelMapper modelMapperMock;

    @InjectMocks
    private PersonalLoanApplicationMapper personalLoanApplicationMapper;

    public void setUp() {
        personalLoanApplicationMapper = new PersonalLoanApplicationMapper();
        ReflectionTestUtils.setField(modelMapperMock, "modelMapper", personalLoanApplicationMapper);
    }

    @Test
    public void mapFromDomainTest() {
        // Given
        PersonalLoanApplication pla = new PersonalLoanApplication("Cliente", "Apellido", "12345678-A", 1000, "EUR");
        PersonalLoanApplicationResponse expectedResponse = new PersonalLoanApplicationResponse();
        expectedResponse.setId(1L);
        expectedResponse.setFirstName("Cliente");
        expectedResponse.setLastName("Apellido");
        expectedResponse.setPersonalId("12345678-A");
        expectedResponse.setAmount(1000);
        expectedResponse.setBadge("EUR");
        expectedResponse.setStatus(PENDING);

        when(modelMapperMock.map(any(), eq(PersonalLoanApplicationResponse.class))).thenReturn(expectedResponse);

        // When
        PersonalLoanApplicationResponse result = personalLoanApplicationMapper.mapFromDomain(pla);

        // Then
        assertEquals(expectedResponse, result);

    }


    @Test
    public void mapToDomainTest() {
        // Given
        PersonalLoanApplicationRequest request = new PersonalLoanApplicationRequest();
        request.setFirstName("Cliente");
        request.setLastName("Apellido");
        request.setPersonalId("12345678-A");
        request.setAmount(1000);
        request.setBadge("EUR");

        PersonalLoanApplication expectedDomain = new PersonalLoanApplication("Cliente", "Apellido", "12345678-A", 1000, "EUR");

        when(modelMapperMock.map(any(), eq(PersonalLoanApplication.class))).thenReturn(expectedDomain);

        // When
        PersonalLoanApplication result = personalLoanApplicationMapper.mapToDomain(request);

        // Then
        assertEquals(expectedDomain, result);

    }
}
