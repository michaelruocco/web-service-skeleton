package uk.co.mruoc.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public abstract class DtoConverter<T> {

    private final ObjectMapper mapper = new ObjectMapper();

    private final Class<T> type;

    public DtoConverter(Class<T> type) {
        this.type = type;
    }

    public T toDto(String json) {
        try {
            return mapper.readValue(json, type);
        } catch (IOException e) {
            throw new CustomerDtoException(e);
        }
    }

    public String toJson(T error) {
        try {
            return mapper.writeValueAsString(error);
        } catch (JsonProcessingException e) {
            throw new CustomerDtoException(e);
        }
    }

}
