package uk.co.mruoc.app.controllers;

import org.junit.Test;

import javax.validation.ConstraintViolationException;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class RestExceptionHandlerTest {

    private final RestExceptionHandler handler = new RestExceptionHandler();

    @Test
    public void shouldHandleCustomerNotFoundException() {
        String accountNumber = "999999999";
        String expectedMessage = String.format("customer with account number %s not found", accountNumber);
        CustomerNotFoundException cause = new CustomerNotFoundException(accountNumber);

        ErrorResponse response = handler.handle(cause);

        assertThat(response.getMessage()).isEqualTo(expectedMessage);
    }

    @Test
    public void shouldHandleConstraintViolationException() {
        FakeConstraintViolation violation = new FakeConstraintViolation();
        ConstraintViolationException cause = new ConstraintViolationException(Collections.singleton(violation));
        String expectedMessage = violation.getPropertyPath() + ": " + violation.getMessage();

        ErrorResponse response = handler.handle(cause);

        assertThat(response.getMessage()).isEqualTo(expectedMessage);
    }

}
