package com.cespinosab.loanapi.model;

import com.cespinosab.loanapi.model.enums.PersonalLoanApplicationStatus;
import jakarta.persistence.*;

import java.time.OffsetDateTime;

/**
 * Represents a Personal Loan Application object
 */
@Entity
@Table(name = "loans", schema = "loans")
public class PersonalLoanApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "first_name", updatable = true, nullable = false)
    private String firstName;

    @Column(name = "last_name", updatable = true, nullable = false)
    private String lastName;

    @Column(name = "personal_id", updatable = true, nullable = false)
    private String personalId;

    @Column(name = "amount", updatable = true, nullable = false)
    private double amount;

    @Column(name = "badge", updatable = true, nullable = false)
    private String badge;

    @Column(name = "status", updatable = true, nullable = false)
    private PersonalLoanApplicationStatus status;

    @Column(name = "created_at", updatable = true, nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "modified_at", updatable = true, nullable = false)
    private OffsetDateTime modifiedAt;

    protected PersonalLoanApplication() {
    }

    /**
     * Constructor.
     *
     * @param firstName first name of the applicant
     * @param lastName last name of the applicant
     * @param personalId personal ID of the applicant
     * @param amount amount of the personal loan
     * @param badge badge of the amount
     */
    public PersonalLoanApplication(String firstName, String lastName, String personalId, double amount, String badge) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalId = personalId;
        this.amount = amount;
        this.badge = badge;
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