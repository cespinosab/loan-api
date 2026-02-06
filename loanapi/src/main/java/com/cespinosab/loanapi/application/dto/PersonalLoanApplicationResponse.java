package com.cespinosab.loanapi.application.dto;

import com.cespinosab.loanapi.application.serialization.PersonalLoanApplicationStatusSerializer;
import com.cespinosab.loanapi.domain.model.enums.PersonalLoanApplicationStatus;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.time.OffsetDateTime;

/**
 * Represent the response of a /loadApplications endpoint
 */
public class PersonalLoanApplicationResponse implements Serializable {

    private Long id;

    private String firstName;

    private String lastName;

    private String personalId;

    private double amount;

    private String badge;

   // @JsonSerialize(using = PersonalLoanApplicationStatusSerializer.class)
    private PersonalLoanApplicationStatus status;

    private OffsetDateTime createdAt;

    private OffsetDateTime modifiedAt;

    public PersonalLoanApplicationResponse() {
    }

    public PersonalLoanApplicationResponse(Long id, String firstName, String lastName, String personalId, double amount,
                                           String badge, PersonalLoanApplicationStatus status, OffsetDateTime createdAt,
                                           OffsetDateTime modifiedAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalId = personalId;
        this.amount = amount;
        this.badge = badge;
        this.status = status;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
