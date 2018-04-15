package uk.co.mruoc.app.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import uk.co.mruoc.api.CustomerNotFoundErrorDto;
import uk.co.mruoc.api.ErrorDtoConverter;

@ControllerAdvice
public class RestExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);

    private final ErrorDtoConverter errorConverter = new ErrorDtoConverter();

    @ExceptionHandler(CustomerNotFoundException.class)
    public ErrorResponse handleNotFound(CustomerNotFoundException e) {
        CustomerNotFoundErrorDto error = new CustomerNotFoundErrorDto(e.getMessage());
        ErrorResponse response = new ErrorResponse(error, HttpStatus.NOT_FOUND);
        String body = errorConverter.toJson(error);
        LOGGER.info(String.format("returning error status %d with body %s", response.getStatusCodeValue(), body));
        return response;
    }

}
