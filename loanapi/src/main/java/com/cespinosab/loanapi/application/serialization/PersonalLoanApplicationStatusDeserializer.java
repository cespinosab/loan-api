package com.cespinosab.loanapi.application.serialization;

import com.cespinosab.loanapi.domain.model.enums.PersonalLoanApplicationStatus;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class PersonalLoanApplicationStatusDeserializer  extends JsonDeserializer<PersonalLoanApplicationStatus> {

        @Override
        public PersonalLoanApplicationStatus deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            String value = p.getText();
            return PersonalLoanApplicationStatus.parseValue(value);
        }

}
