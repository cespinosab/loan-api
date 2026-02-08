package com.cespinosab.loanapi.application.dto;

import com.cespinosab.loanapi.domain.model.enums.PersonalLoanApplicationStatus;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PersonalLoanApplicationRequest that = (PersonalLoanApplicationRequest) o;
        return Double.compare(amount, that.amount) == 0 && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(personalId, that.personalId) && Objects.equals(badge, that.badge) && status == that.status && Objects.equals(createdAt, that.createdAt) && Objects.equals(modifiedAt, that.modifiedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, personalId, amount, badge, status, createdAt, modifiedAt);
    }
}