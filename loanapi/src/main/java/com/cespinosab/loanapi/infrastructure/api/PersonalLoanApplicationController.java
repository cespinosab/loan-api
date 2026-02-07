package com.cespinosab.loanapi.infrastructure.api;

import com.cespinosab.loanapi.application.dto.PersonalLoanApplicationRequest;
import com.cespinosab.loanapi.application.dto.PersonalLoanApplicationResponse;
import com.cespinosab.loanapi.application.service.CreatePersonalLoanApplicationService;
import com.cespinosab.loanapi.application.service.GetPersonalLoanApplicationService;
import com.cespinosab.loanapi.application.service.UpdatePersonalLoanApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/personalLoanApplications", produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonalLoanApplicationController {

    private final GetPersonalLoanApplicationService getPersonalLoanApplicationService;
    private final CreatePersonalLoanApplicationService createPersonalLoanApplicationService;
    private final UpdatePersonalLoanApplicationService updatePersonalLoanApplicationService;

    PersonalLoanApplicationController(GetPersonalLoanApplicationService getPersonalLoanApplicationService,
                                      CreatePersonalLoanApplicationService createPersonalLoanApplicationService,
                                      UpdatePersonalLoanApplicationService updatePersonalLoanApplicationService ) {
        this.getPersonalLoanApplicationService = getPersonalLoanApplicationService;
        this.createPersonalLoanApplicationService = createPersonalLoanApplicationService;
        this.updatePersonalLoanApplicationService = updatePersonalLoanApplicationService;
    }

    @GetMapping
    ResponseEntity<List<PersonalLoanApplicationResponse>> getAll() {
        List<PersonalLoanApplicationResponse> responseListBody = getPersonalLoanApplicationService.getAll();
        return new ResponseEntity<>(responseListBody, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<PersonalLoanApplicationResponse> getPersonalLoanApplicationById(@PathVariable Long id) {
        PersonalLoanApplicationResponse responseBody = getPersonalLoanApplicationService.getById(id);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PersonalLoanApplicationResponse> create(@RequestBody PersonalLoanApplicationRequest body) {
        PersonalLoanApplicationResponse responseBody = createPersonalLoanApplicationService.create(body);
        return new ResponseEntity<>(responseBody, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonalLoanApplicationResponse> update(@PathVariable Long id, @RequestBody PersonalLoanApplicationRequest body) {
        PersonalLoanApplicationResponse responseBody = updatePersonalLoanApplicationService.update(id, body);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

}