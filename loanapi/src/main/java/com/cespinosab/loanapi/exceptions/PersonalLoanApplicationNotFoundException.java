package com.cespinosab.loanapi.exceptions;

import com.cespinosab.loanapi.model.PersonalLoanApplication;

/**
 * Exception to indicate that a {@link PersonalLoanApplication} has not found
 */
public class PersonalLoanApplicationNotFoundException extends RuntimeException {

    public PersonalLoanApplicationNotFoundException(Long id) {
        super("Personal Loan Application with id: '" + id + "' not found.");
    }
}
