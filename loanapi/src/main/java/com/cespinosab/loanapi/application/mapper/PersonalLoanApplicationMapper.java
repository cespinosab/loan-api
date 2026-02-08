package com.cespinosab.loanapi.application.mapper;

import com.cespinosab.loanapi.application.dto.PersonalLoanApplicationRequest;
import com.cespinosab.loanapi.application.dto.PersonalLoanApplicationResponse;
import com.cespinosab.loanapi.domain.model.PersonalLoanApplication;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonalLoanApplicationMapper {

    private final ModelMapper modelMapper;

    public PersonalLoanApplicationMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PersonalLoanApplicationResponse mapFromDomain(PersonalLoanApplication personalLoanApplication) {
        modelMapper.typeMap(PersonalLoanApplication.class, PersonalLoanApplicationResponse.class);
        return modelMapper.map(personalLoanApplication, PersonalLoanApplicationResponse.class);
    }

    public PersonalLoanApplication mapToDomain(PersonalLoanApplicationRequest request) {
        modelMapper.typeMap(PersonalLoanApplicationRequest.class, PersonalLoanApplication.class);
        return modelMapper.map(request, PersonalLoanApplication.class);
    }

}
