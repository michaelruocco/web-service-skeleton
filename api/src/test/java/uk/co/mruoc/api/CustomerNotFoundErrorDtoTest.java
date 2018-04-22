package uk.co.mruoc.api;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerNotFoundErrorDtoTest {

    private static final int SC_NOT_FOUND = 404;
    private static final String ACCOUNT_NUMBER = "9999999999";

    private final CustomerNotFoundErrorDto error = new CustomerNotFoundErrorDto(ACCOUNT_NUMBER);

    @Test
    public void shouldReturnMessage() {
        String expectedMessage = String.format("customer with account number %s not found", ACCOUNT_NUMBER);

        assertThat(error.getMessage()).isEqualTo(expectedMessage);
    }

    @Test
    public void shouldReturnNotFoundStatusCode() {
        assertThat(error.getStatusCode()).isEqualTo(SC_NOT_FOUND);
    }

    @Test
    public void shouldReturnAccountNumber() {
        assertThat(error.getAccountNumber()).isEqualTo(ACCOUNT_NUMBER);
    }

}
