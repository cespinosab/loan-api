package com.cespinosab.loanapi.infrastructure.api;

import com.cespinosab.loanapi.application.dto.PersonalLoanApplicationRequest;
import com.cespinosab.loanapi.application.dto.PersonalLoanApplicationResponse;
import com.cespinosab.loanapi.application.service.CreatePersonalLoanApplicationService;
import com.cespinosab.loanapi.application.service.GetPersonalLoanApplicationService;
import com.cespinosab.loanapi.application.service.UpdatePersonalLoanApplicationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static com.cespinosab.loanapi.domain.model.enums.PersonalLoanApplicationStatus.APPROVED;
import static com.cespinosab.loanapi.domain.model.enums.PersonalLoanApplicationStatus.PENDING;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Test suite for {@link PersonalLoanApplicationController}
 */
@ExtendWith(MockitoExtension.class)
public class PersonalLoanApplicationControllerTest {

    @Mock
    private GetPersonalLoanApplicationService getPersonalLoanApplicationServiceMock;

    @Mock
    private CreatePersonalLoanApplicationService createPersonalLoanApplicationServiceMock;

    @Mock
    private UpdatePersonalLoanApplicationService updatePersonalLoanApplicationServiceMock;

    @InjectMocks
    private PersonalLoanApplicationController personalLoanApplicationController;

    @Test
    public void getAllTest() {
        // Given
        PersonalLoanApplicationResponse plaResponse1 = new PersonalLoanApplicationResponse();
        plaResponse1.setId(1L);
        plaResponse1.setFirstName("Cliente1");
        plaResponse1.setLastName("Apellido1");
        plaResponse1.setStatus(PENDING);

        PersonalLoanApplicationResponse plaResponse2 = new PersonalLoanApplicationResponse();
        plaResponse2.setId(2L);
        plaResponse2.setFirstName("Cliente2");
        plaResponse2.setLastName("Apellido2");
        plaResponse2.setStatus(APPROVED);

        when(getPersonalLoanApplicationServiceMock.getAll()).thenReturn(List.of(plaResponse1, plaResponse2));

        // When
        ResponseEntity<List<PersonalLoanApplicationResponse>> response = personalLoanApplicationController.getAll();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(List.of(plaResponse1, plaResponse2), response.getBody());
        verify(getPersonalLoanApplicationServiceMock).getAll();
        verifyNoMoreInteractions(getPersonalLoanApplicationServiceMock);
    }

    @Test
    public void getByIdTest() {
        // Given
        PersonalLoanApplicationResponse plaResponseMock = new PersonalLoanApplicationResponse();
        plaResponseMock.setId(1L);
        plaResponseMock.setFirstName("Cliente1");
        plaResponseMock.setLastName("Apellido1");
        plaResponseMock.setStatus(PENDING);
        when(getPersonalLoanApplicationServiceMock.getById(any())).thenReturn(plaResponseMock);

        // When
        ResponseEntity<PersonalLoanApplicationResponse> response = personalLoanApplicationController.getById(1L);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(plaResponseMock, response.getBody());
        verify(getPersonalLoanApplicationServiceMock).getById(any());
        verifyNoMoreInteractions(getPersonalLoanApplicationServiceMock);
    }

    @Test
    public void createTest() {
        // Given
        PersonalLoanApplicationResponse plaResponseMock = new PersonalLoanApplicationResponse();
        plaResponseMock.setId(1L);
        plaResponseMock.setFirstName("Cliente1");
        plaResponseMock.setLastName("Apellido1");
        plaResponseMock.setStatus(PENDING);
        plaResponseMock.setAmount(1000);
        when(createPersonalLoanApplicationServiceMock.create(any())).thenReturn(plaResponseMock);

        // When
        PersonalLoanApplicationRequest plaRequest = new PersonalLoanApplicationRequest();
        plaRequest.setFirstName("Cliente1");
        plaRequest.setLastName("Apellido1");
        plaRequest.setAmount(1000);

        ResponseEntity<PersonalLoanApplicationResponse> response = personalLoanApplicationController.create(plaRequest);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(plaResponseMock, response.getBody());
        verify(createPersonalLoanApplicationServiceMock).create(any());
        verifyNoMoreInteractions(createPersonalLoanApplicationServiceMock);
    }

    @Test
    public void updateTest() {
        // Given
        PersonalLoanApplicationResponse plaResponseMock = new PersonalLoanApplicationResponse();
        plaResponseMock.setId(1L);
        plaResponseMock.setFirstName("Cliente1");
        plaResponseMock.setLastName("Apellido1");
        plaResponseMock.setStatus(APPROVED);
        plaResponseMock.setAmount(4000);
        when(updatePersonalLoanApplicationServiceMock.update(any(), any())).thenReturn(plaResponseMock);

        // When
        PersonalLoanApplicationRequest plaRequest = new PersonalLoanApplicationRequest();
        plaRequest.setFirstName("Cliente1");
        plaRequest.setLastName("Apellido1");
        plaResponseMock.setStatus(APPROVED);
        plaRequest.setAmount(4000);

        ResponseEntity<PersonalLoanApplicationResponse> response = personalLoanApplicationController.update(1L, plaRequest);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(plaResponseMock, response.getBody());
        verify(updatePersonalLoanApplicationServiceMock).update(any(), any());
        verifyNoMoreInteractions(updatePersonalLoanApplicationServiceMock);
    }
}
