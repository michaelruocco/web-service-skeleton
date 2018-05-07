package uk.co.mruoc.api;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerAlreadyExistsErrorDtoTest {

    private static final int CONFLICT = 409;
    private static final String ACCOUNT_NUMBER = "9999999999";

    private final CustomerAlreadyExistsErrorDto error = new CustomerAlreadyExistsErrorDto(ACCOUNT_NUMBER);

    @Test
    public void shouldReturnMessage() {
        String expectedMessage = String.format("customer with account number %s already exists", ACCOUNT_NUMBER);

        assertThat(error.getMessage()).isEqualTo(expectedMessage);
    }

    @Test
    public void shouldReturnNotFoundStatusCode() {
        assertThat(error.getStatusCode()).isEqualTo(CONFLICT);
    }

    @Test
    public void shouldReturnAccountNumber() {
        assertThat(error.getAccountNumber()).isEqualTo(ACCOUNT_NUMBER);
    }

}
