package uk.co.mruoc.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class CustomerDtoConverter {

    private final ObjectMapper mapper = new ObjectMapper();

    public CustomerDto toCustomer(String json) {
        try {
            return mapper.readValue(json, CustomerDto.class);
        } catch (IOException e) {
            throw new CustomerDtoException(e);
        }
    }

    public String toJson(CustomerDto customer) {
        try {
            return mapper.writeValueAsString(customer);
        } catch (JsonProcessingException e) {
            throw new CustomerDtoException(e);
        }
    }

}
