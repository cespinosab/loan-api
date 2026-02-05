package com.cespinosab.loanapi.api.v1.dto;

import com.cespinosab.loanapi.model.enums.PersonalLoanApplicationStatus;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;
import java.time.OffsetDateTime;

/**
 * Represent the body of a request of /v1/personalLoanApplications endpoint
 */
public class PersonalLoanApplicationRequest implements Serializable {

    private String firstName;

    private String lastName;

    private String personalId;

    private double amount;

    private String badge;

    @JsonDeserialize(using = JsonDeserializer.class)
    private PersonalLoanApplicationStatus status;

    private OffsetDateTime createdAt;

    private OffsetDateTime modifiedAt;
}