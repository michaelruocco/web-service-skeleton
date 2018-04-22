package uk.co.mruoc.api;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InvalidAccountNumberFormatErrorDtoTest {

    private static final int SC_BAD_REQUEST = 400;
    private static final String ACCOUNT_NUMBER = "999999999a";

    private final InvalidAccountNumberFormatErrorDto error = new InvalidAccountNumberFormatErrorDto(ACCOUNT_NUMBER);

    @Test
    public void shouldReturnMessage() {
        String expectedMessage = String.format("invalid account number, should be 10 digit numeric: %s", ACCOUNT_NUMBER);

        assertThat(error.getMessage()).isEqualTo(expectedMessage);
    }

    @Test
    public void shouldReturnBadRequestStatusCode() {
        assertThat(error.getStatusCode()).isEqualTo(SC_BAD_REQUEST);
    }

    @Test
    public void shouldReturnAccountNumber() {
        assertThat(error.getAccountNumber()).isEqualTo(ACCOUNT_NUMBER);
    }

}
