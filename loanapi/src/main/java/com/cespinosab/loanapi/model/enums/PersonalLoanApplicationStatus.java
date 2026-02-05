package com.cespinosab.loanapi.model.enums;

import com.cespinosab.loanapi.model.PersonalLoanApplication;

import java.util.Arrays;

/**
 * Status of the {@link PersonalLoanApplication}
 */
public enum PersonalLoanApplicationStatus {

    PENDING("Pendiente"),

    APPROVED("Aprobada"),

    REJECTED("Rechazada"),

    CANCELLED("Cancelada");


    private final String value;

    /**
     * Constructor
     *
     * @param value String containing the value of a status
     */
    PersonalLoanApplicationStatus(String value) {
        this.value = value;
    }

    /**
     * Parse a value from a String to a PersonalLoanApplicationStatus
     *
     * @param value String with the value to be parsed
     * @return the status associated with the value received
     * @throws IllegalArgumentException if the value received is not valid
     */
    public static PersonalLoanApplicationStatus parseValue(String value) {
        for (PersonalLoanApplicationStatus status : PersonalLoanApplicationStatus.values()) {
            if (status.value.equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("The value '" + value + "' is not a associated with a " +
                "valid personal loan application status: " + Arrays.stream(PersonalLoanApplicationStatus.values()));
    }

}
