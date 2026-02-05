package com.cespinosab.loanapi.service;

import com.cespinosab.loanapi.exceptions.PersonalLoanApplicationNotFoundException;
import com.cespinosab.loanapi.model.PersonalLoanApplication;
import com.cespinosab.loanapi.model.enums.PersonalLoanApplicationStatus;
import com.cespinosab.loanapi.repository.PersonalLoanApplicationRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import static com.cespinosab.loanapi.model.enums.PersonalLoanApplicationStatus.PENDING;
import static com.cespinosab.loanapi.model.enums.PersonalLoanApplicationStatus.APPROVED;
import static com.cespinosab.loanapi.model.enums.PersonalLoanApplicationStatus.REJECTED;
import static com.cespinosab.loanapi.model.enums.PersonalLoanApplicationStatus.CANCELLED;

/**
 * Service responsible to manage Personal Loan Applications
 */
@Service
public class PersonalLoanApplicationService {

    private static final Logger LOG = LogManager.getLogManager().getLogger(PersonalLoanApplicationService.class.getName());

    private final PersonalLoanApplicationRepository personalLoanApplicationRepository;

    public PersonalLoanApplicationService(PersonalLoanApplicationRepository personalLoanApplicationRepository) {
        this.personalLoanApplicationRepository = personalLoanApplicationRepository;
    }

    /**
     * Get the list of all existing personal loan applications
     *
     * @return the list of all existing personal loan applications
     */
    public List<PersonalLoanApplication> getAllPersonalLoanApplications() {
        return personalLoanApplicationRepository.findAll();
    }

    /**
     * Get a personal loan application by its ID
     *
     * @param id ID of the personal loan application to search
     * @return the personal loan application
     * @throws PersonalLoanApplicationNotFoundException if it does not exist
     */
    public PersonalLoanApplication getPersonalLoanApplicationById(Long id) {
        return personalLoanApplicationRepository.findById(id)
                .orElseThrow(() -> new PersonalLoanApplicationNotFoundException(id));
    }

    /**
     * Create a new personal loan application
     *
     * @param personalLoanApplication data to create
     * @return the created personal loan application
     */
    public PersonalLoanApplication createPersonalLoanApplication(PersonalLoanApplication personalLoanApplication) {
        return personalLoanApplicationRepository.save(personalLoanApplication);
    }

    /**
     * Update an existing personal loan application with the new data or create a new if it does not exist.
     *
     * @param newPersonalLoanApplication personal application with data to update
     * @param id                         ID of the personal loan application to update
     * @return the personal loan application updated or created
     */
    public PersonalLoanApplication updatePersonalLoanApplication(PersonalLoanApplication newPersonalLoanApplication, Long id) {
        return personalLoanApplicationRepository.findById(id)
                .map(existingPersonalLoanApplication -> {
                    existingPersonalLoanApplication.setFirstName(newPersonalLoanApplication.getFirstName());
                    existingPersonalLoanApplication.setLastName(newPersonalLoanApplication.getLastName());
                    existingPersonalLoanApplication.setPersonalId(newPersonalLoanApplication.getPersonalId());
                    existingPersonalLoanApplication.setAmount(newPersonalLoanApplication.getAmount());
                    existingPersonalLoanApplication.setBadge(newPersonalLoanApplication.getBadge());
                    existingPersonalLoanApplication.setModifiedAt(OffsetDateTime.now());

                    if (isValidStatusChange(existingPersonalLoanApplication.getStatus(), newPersonalLoanApplication.getStatus())) {
                        existingPersonalLoanApplication.setStatus(newPersonalLoanApplication.getStatus());
                    }
                    return personalLoanApplicationRepository.save(existingPersonalLoanApplication);
                })
                .orElseGet(() -> {
                    return createPersonalLoanApplication(newPersonalLoanApplication);
                });
    }

    /**
     * Check if the target status is valid depending on the original status
     *
     * @param originStatus origin status
     * @param targetStatus target status to check if it is valid
     * @return true if the targetStatus is valid; otherwise, false
     */
    private boolean isValidStatusChange(PersonalLoanApplicationStatus originStatus, PersonalLoanApplicationStatus targetStatus) {
        return switch (originStatus) {
            case PENDING -> Arrays.asList(APPROVED, REJECTED).contains(targetStatus);
            case APPROVED -> CANCELLED == targetStatus;
            default -> false;
        };
    }
}