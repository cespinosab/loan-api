package com.cespinosab.loanapi.domain.model.enums;

import com.cespinosab.loanapi.domain.model.PersonalLoanApplication;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Status of the {@link PersonalLoanApplication}
 */
public enum PersonalLoanApplicationStatus {

    PENDING("PENDIENTE"),

    APPROVED("APROBADA"),

    REJECTED("RECHAZADA"),

    CANCELLED("CANCELADA");


    private final String value;

    /**
     * Constructor
     *
     * @param value String containing the value of a status
     */
    PersonalLoanApplicationStatus(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    /**
     * Parse a value from a String to a PersonalLoanApplicationStatus
     *
     * @param value String with the value to be parsed
     * @return the status associated with the value received
     */
    @JsonCreator
    public static PersonalLoanApplicationStatus parseValue(String value) {
        for (PersonalLoanApplicationStatus status : PersonalLoanApplicationStatus.values()) {
            if (status.value.equalsIgnoreCase(value)) {
                return status;
            }
        }
        return null;
    }

}
