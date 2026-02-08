package com.cespinosab.loanapi.application.service;

import com.cespinosab.loanapi.application.dto.PersonalLoanApplicationRequest;
import com.cespinosab.loanapi.application.dto.PersonalLoanApplicationResponse;
import com.cespinosab.loanapi.application.mapper.PersonalLoanApplicationMapper;
import com.cespinosab.loanapi.domain.model.PersonalLoanApplication;
import com.cespinosab.loanapi.domain.model.enums.PersonalLoanApplicationStatus;
import com.cespinosab.loanapi.infrastructure.repository.PersonalLoanApplicationRepository;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Arrays;

import static com.cespinosab.loanapi.domain.model.enums.PersonalLoanApplicationStatus.*;

/**
 * Service responsible to manage Personal Loan Applications
 */
@Service
public class UpdatePersonalLoanApplicationService {

    private static final Logger LOG = LogManager.getLogger(UpdatePersonalLoanApplicationService.class);

    private final PersonalLoanApplicationRepository repository;
    private final PersonalLoanApplicationMapper mapper;

    public UpdatePersonalLoanApplicationService(PersonalLoanApplicationRepository repository,
                                                PersonalLoanApplicationMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Update an existing personal loan application with the new data or create a new if it does not exist.
     *
     * @param id      ID of the personal loan application to update
     * @param request personal loan application with data to update
     * @return the personal loan application updated or created
     */
    @Transactional
    public PersonalLoanApplicationResponse update(Long id, PersonalLoanApplicationRequest request) {
        PersonalLoanApplication newPersonalLoanApplication = mapper.mapToDomain(request);
        OffsetDateTime now = OffsetDateTime.now();
        return repository.findById(id)
                .map(existingPersonalLoanApplication -> {

                    LOG.debug("Updating personal application loan with id {}...", id);
                    existingPersonalLoanApplication.setFirstName(newPersonalLoanApplication.getFirstName());
                    existingPersonalLoanApplication.setLastName(newPersonalLoanApplication.getLastName());
                    existingPersonalLoanApplication.setPersonalId(newPersonalLoanApplication.getPersonalId());
                    existingPersonalLoanApplication.setAmount(newPersonalLoanApplication.getAmount());
                    existingPersonalLoanApplication.setBadge(newPersonalLoanApplication.getBadge());
                    existingPersonalLoanApplication.setModifiedAt(now);

                    if (isValidStatusChange(existingPersonalLoanApplication.getStatus(), newPersonalLoanApplication.getStatus())) {
                        existingPersonalLoanApplication.setStatus(newPersonalLoanApplication.getStatus());
                    }
                    PersonalLoanApplication updated = repository.save(existingPersonalLoanApplication);
                    return mapper.mapFromDomain(updated);
                })
                .orElseGet(() -> {
                    LOG.debug("Personal application loan with id {} does not exist. Creating...", id);
                    newPersonalLoanApplication.setCreatedAt(now);
                    newPersonalLoanApplication.setModifiedAt(now);
                    PersonalLoanApplication created = repository.save(newPersonalLoanApplication);
                    return mapper.mapFromDomain(created);
                });
    }

    /**
     * Check if the target status is valid depending on the original status
     *
     * @param originStatus origin status
     * @param targetStatus target status to check if it is valid
     * @return true if the targetStatus is valid; otherwise, false
     */
    public boolean isValidStatusChange(PersonalLoanApplicationStatus originStatus, PersonalLoanApplicationStatus targetStatus) {
        return switch (originStatus) {
            case PENDING -> Arrays.asList(APPROVED, REJECTED).contains(targetStatus);
            case APPROVED -> CANCELLED == targetStatus;
            default -> false;
        };
    }
}