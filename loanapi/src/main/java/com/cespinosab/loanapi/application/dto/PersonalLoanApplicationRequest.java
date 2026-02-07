package com.cespinosab.loanapi.application.dto;

import com.cespinosab.loanapi.domain.model.enums.PersonalLoanApplicationStatus;

import java.io.Serializable;
import java.time.OffsetDateTime;

/**
 * Represent the body of a request of /personalLoanApplications endpoint
 */
public class PersonalLoanApplicationRequest implements Serializable {

    private String firstName;

    private String lastName;

    private String personalId;

    private double amount;

    private String badge;

    private PersonalLoanApplicationStatus status;

    private OffsetDateTime createdAt;

    private OffsetDateTime modifiedAt;

    public PersonalLoanApplicationRequest() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public PersonalLoanApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(PersonalLoanApplicationStatus status) {
        this.status = status;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OffsetDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(OffsetDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}