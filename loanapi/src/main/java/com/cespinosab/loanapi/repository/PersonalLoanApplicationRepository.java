package com.cespinosab.loanapi.repository;

import com.cespinosab.loanapi.model.PersonalLoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * JPA repository for {@link PersonalLoanApplication}
 */
public interface PersonalLoanApplicationRepository extends JpaRepository<PersonalLoanApplication, Long>, JpaSpecificationExecutor<PersonalLoanApplication> {

}