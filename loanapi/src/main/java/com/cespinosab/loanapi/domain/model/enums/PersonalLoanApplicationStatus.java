package com.cespinosab.loanapi.domain.model.enums;

import com.cespinosab.loanapi.domain.model.PersonalLoanApplication;
import com.fasterxml.jackson.annotation.JsonValue;

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
     */
    @JsonValue
    public static PersonalLoanApplicationStatus parseValue(String value) {
        for (PersonalLoanApplicationStatus status : PersonalLoanApplicationStatus.values()) {
            if (status.value.equalsIgnoreCase(value)) {
                return status;
            }
        }
        return null;
    }

}
