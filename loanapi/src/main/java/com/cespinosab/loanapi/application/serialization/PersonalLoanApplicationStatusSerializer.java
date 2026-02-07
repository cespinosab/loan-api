package com.cespinosab.loanapi.application.serialization;

import com.cespinosab.loanapi.domain.model.enums.PersonalLoanApplicationStatus;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.stereotype.Component;

import java.io.IOException;


public class PersonalLoanApplicationStatusSerializer extends JsonSerializer<PersonalLoanApplicationStatus> {

    @Override
    public void serialize(PersonalLoanApplicationStatus status, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (status == null) {
            gen.writeNull();
            return;
        }
        gen.writeString(status.getValue());
    }
}
