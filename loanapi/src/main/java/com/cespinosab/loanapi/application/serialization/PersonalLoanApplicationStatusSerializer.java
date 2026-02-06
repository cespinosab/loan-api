package com.cespinosab.loanapi.application.serialization;

import com.cespinosab.loanapi.domain.model.enums.PersonalLoanApplicationStatus;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class PersonalLoanApplicationStatusSerializer extends JsonSerializer<String> {

        @Override
        public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeString(value.toUpperCase());
        }
}
