package com.cespinosab.loanapi.application.dto;

import com.cespinosab.loanapi.domain.model.enums.PersonalLoanApplicationStatus;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;

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

    private PersonalLoanApplicationStatus status;

    private OffsetDateTime createdAt;

    private OffsetDateTime modifiedAt;

    public PersonalLoanApplicationResponse() {
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PersonalLoanApplicationResponse response = (PersonalLoanApplicationResponse) o;
        return Double.compare(amount, response.amount) == 0 && Objects.equals(id, response.id) && Objects.equals(firstName, response.firstName) && Objects.equals(lastName, response.lastName) && Objects.equals(personalId, response.personalId) && Objects.equals(badge, response.badge) && status == response.status && Objects.equals(createdAt, response.createdAt) && Objects.equals(modifiedAt, response.modifiedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, personalId, amount, badge, status, createdAt, modifiedAt);
    }
}
