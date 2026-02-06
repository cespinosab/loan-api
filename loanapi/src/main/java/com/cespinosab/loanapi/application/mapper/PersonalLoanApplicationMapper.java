package com.cespinosab.loanapi.application.mapper;

import com.cespinosab.loanapi.application.dto.PersonalLoanApplicationRequest;
import com.cespinosab.loanapi.application.dto.PersonalLoanApplicationResponse;
import com.cespinosab.loanapi.domain.model.PersonalLoanApplication;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PersonalLoanApplicationMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public PersonalLoanApplicationMapper() {
        modelMapper.typeMap(PersonalLoanApplicationRequest.class, PersonalLoanApplication.class);
    }

    public PersonalLoanApplicationResponse mapFromDomain(PersonalLoanApplication personalLoanApplication) {
        return modelMapper.map(personalLoanApplication, PersonalLoanApplicationResponse.class);
    }

    public PersonalLoanApplication mapToDomain(PersonalLoanApplicationRequest request){
       return modelMapper.map(request, PersonalLoanApplication.class);
    }

}
