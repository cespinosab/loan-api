package com.cespinosab.loanapi.application.service;

import com.cespinosab.loanapi.application.dto.PersonalLoanApplicationResponse;
import com.cespinosab.loanapi.application.mapper.PersonalLoanApplicationMapper;
import com.cespinosab.loanapi.domain.exceptions.PersonalLoanApplicationNotFoundException;
import com.cespinosab.loanapi.infrastructure.repository.PersonalLoanApplicationRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service responsible to manage Personal Loan Applications
 */
@Service
public class GetPersonalLoanApplicationService {

    private static final Logger LOG = LogManager.getLogger(GetPersonalLoanApplicationService.class);

    private final PersonalLoanApplicationRepository repository;
    private final PersonalLoanApplicationMapper mapper;

    public GetPersonalLoanApplicationService(PersonalLoanApplicationRepository repository,
                                             PersonalLoanApplicationMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Get the list of all existing personal loan applications
     *
     * @return the list of all existing personal loan applications response
     */
    public List<PersonalLoanApplicationResponse> getAll() {
        LOG.debug("Getting all personal loan applications...");
        return repository.findAll()
                .stream()
                .map(mapper::mapFromDomain)
                .collect(Collectors.toList());

    }

    /**
     * Get a personal loan application by its ID
     *
     * @param id ID of the personal loan application to search
     * @return the personal loan application response
     * @throws PersonalLoanApplicationNotFoundException if it does not exist
     */
    public PersonalLoanApplicationResponse getById(Long id) {
        LOG.debug("Getting personal loan application with id {}...", id);
        return repository.findById(id)
                .map(mapper::mapFromDomain)
                .orElseThrow(() -> new PersonalLoanApplicationNotFoundException(id));
    }
}