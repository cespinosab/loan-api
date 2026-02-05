package com.cespinosab.loanapi.api.v1.mapper;

import com.cespinosab.loanapi.api.v1.dto.PersonalLoanApplicationRequest;
import com.cespinosab.loanapi.api.v1.dto.PersonalLoanApplicationResponse;
import com.cespinosab.loanapi.model.PersonalLoanApplication;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PersonalLoanApplicationMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public PersonalLoanApplicationMapper() {
        modelMapper.typeMap(PersonalLoanApplicationRequest.class, PersonalLoanApplication.class);
    }

    public PersonalLoanApplicationResponse mapFromPersonalLoanApplication(PersonalLoanApplication personalLoanApplication) {
        return modelMapper.map(personalLoanApplication, PersonalLoanApplicationResponse.class);
    }

    public PersonalLoanApplication mapToPersonalLoanApplication(PersonalLoanApplicationRequest request){
       return modelMapper.map(request, PersonalLoanApplication.class);
    }

}
