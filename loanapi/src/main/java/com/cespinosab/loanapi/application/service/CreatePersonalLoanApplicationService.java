package com.cespinosab.loanapi.application.service;

import com.cespinosab.loanapi.application.dto.PersonalLoanApplicationRequest;
import com.cespinosab.loanapi.application.dto.PersonalLoanApplicationResponse;
import com.cespinosab.loanapi.application.mapper.PersonalLoanApplicationMapper;
import com.cespinosab.loanapi.domain.model.PersonalLoanApplication;
import com.cespinosab.loanapi.infrastructure.repository.PersonalLoanApplicationRepository;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

/**
 * Service responsible to manage Personal Loan Applications
 */
@Service
public class CreatePersonalLoanApplicationService {

    private static final Logger LOG = LogManager.getLogger(CreatePersonalLoanApplicationService.class);

    private final PersonalLoanApplicationRepository repository;
    private final PersonalLoanApplicationMapper mapper;

    public CreatePersonalLoanApplicationService(PersonalLoanApplicationRepository repository,
                                                PersonalLoanApplicationMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Create a new personal loan application
     *
     * @param request data to create
     * @return the created personal loan application
     */
    @Transactional
    public PersonalLoanApplicationResponse create(PersonalLoanApplicationRequest request) {
        LOG.debug("Creating personal application loan...");
        PersonalLoanApplication newPersonalLoanApplication = mapper.mapToDomain(request);
        OffsetDateTime now = OffsetDateTime.now();
        newPersonalLoanApplication.setCreatedAt(now);
        newPersonalLoanApplication.setModifiedAt(now);

        PersonalLoanApplication saved = repository.save(newPersonalLoanApplication);
        return mapper.mapFromDomain(saved);
    }


}