package uk.co.mruoc.app.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import uk.co.mruoc.api.CustomerNotFoundErrorDto;
import uk.co.mruoc.api.ErrorDto;
import uk.co.mruoc.api.ErrorDto.ErrorDtoBuilder;
import uk.co.mruoc.api.ErrorDtoConverter;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class RestExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);

    private final ErrorDtoConverter errorConverter = new ErrorDtoConverter();

    @ExceptionHandler(CustomerNotFoundException.class)
    public ErrorResponse handle(CustomerNotFoundException e) {
        CustomerNotFoundErrorDto error = new CustomerNotFoundErrorDto(e.getMessage());
        ErrorResponse response = new ErrorResponse(error, HttpStatus.NOT_FOUND);
        return handle(response);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ErrorResponse handle(ConstraintViolationException e) {
        ErrorDto error = new ErrorDtoBuilder().setMessage(e.getMessage()).build();
        ErrorResponse response = new ErrorResponse(error, HttpStatus.BAD_REQUEST);
        return handle(response);
    }

    private ErrorResponse handle(ErrorResponse response) {
        String body = errorConverter.toJson(response.getBody());
        LOGGER.info(String.format("returning error status %d with body %s", response.getStatusCodeValue(), body));
        return response;
    }

}
