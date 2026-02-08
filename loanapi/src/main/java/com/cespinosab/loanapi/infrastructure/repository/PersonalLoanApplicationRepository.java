package com.cespinosab.loanapi.infrastructure.repository;

import com.cespinosab.loanapi.domain.model.PersonalLoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * JPA repository for {@link PersonalLoanApplication}
 */
@Repository
public interface PersonalLoanApplicationRepository extends JpaRepository<PersonalLoanApplication, Long>, JpaSpecificationExecutor<PersonalLoanApplication> {

}