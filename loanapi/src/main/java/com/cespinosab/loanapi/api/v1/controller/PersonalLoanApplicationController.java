package com.cespinosab.loanapi.api.v1.controller;

import com.cespinosab.loanapi.api.v1.dto.PersonalLoanApplicationRequest;
import com.cespinosab.loanapi.api.v1.dto.PersonalLoanApplicationResponse;
import com.cespinosab.loanapi.api.v1.mapper.PersonalLoanApplicationMapper;
import com.cespinosab.loanapi.model.PersonalLoanApplication;
import com.cespinosab.loanapi.service.PersonalLoanApplicationService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/personalLoadApplications", produces = MediaType.APPLICATION_JSON_VALUE)
class PersonalLoanApplicationController {

    private final PersonalLoanApplicationService personalLoanApplicationService;
    private final PersonalLoanApplicationMapper personalLoanApplicationMapper;

    PersonalLoanApplicationController(PersonalLoanApplicationService personalLoanApplicationService,
                                      PersonalLoanApplicationMapper personalLoanApplicationMapper) {
        this.personalLoanApplicationService = personalLoanApplicationService;
        this.personalLoanApplicationMapper = personalLoanApplicationMapper;
    }

    @GetMapping
    List<PersonalLoanApplication> getAll() {
        return personalLoanApplicationService.getAllPersonalLoanApplications();
    }

    @PostMapping
    PersonalLoanApplicationResponse newPersonalLoanApplication(@RequestBody PersonalLoanApplicationRequest body, HttpServletResponse response) {
        PersonalLoanApplication newPersonalLoanApplication = personalLoanApplicationMapper.mapToPersonalLoanApplication(body);
        PersonalLoanApplication created = personalLoanApplicationService.createPersonalLoanApplication(newPersonalLoanApplication);
        response.setStatus(HttpStatus.CREATED.value());
        return personalLoanApplicationMapper.mapFromPersonalLoanApplication(created);
    }

    // Single item
    @GetMapping("/{id}")
    PersonalLoanApplicationResponse getPersonalLoanApplicationById(@PathVariable Long id) {
        return personalLoanApplicationMapper.mapFromPersonalLoanApplication(personalLoanApplicationService.getPersonalLoanApplicationById(id));
    }

    @PutMapping("/{id}")
    PersonalLoanApplicationResponse updatePersonalApplicationLoan(@RequestBody PersonalLoanApplicationRequest body, @PathVariable Long id,
                                                                  HttpServletResponse response) {
        PersonalLoanApplication newPersonalLoanApplication = personalLoanApplicationMapper.mapToPersonalLoanApplication(body);
        PersonalLoanApplication updated = personalLoanApplicationService.updatePersonalLoanApplication(newPersonalLoanApplication, id);
        response.setStatus(HttpStatus.OK.value());
        return personalLoanApplicationMapper.mapFromPersonalLoanApplication(updated);
    }

}