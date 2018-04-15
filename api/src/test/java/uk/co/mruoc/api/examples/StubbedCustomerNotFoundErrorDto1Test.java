package uk.co.mruoc.api.examples;

import org.junit.Test;
import uk.co.mruoc.api.CustomerNotFoundErrorDto;

import static org.assertj.core.api.Assertions.assertThat;

public class StubbedCustomerNotFoundErrorDto1Test {

    private static final String ACCOUNT_NUMBER = "9999999999";

    private final CustomerNotFoundErrorDto error = new StubbedCustomerNotFoundErrorDto1();

    @Test
    public void shouldReturnMessage() {
        String expectedMessage = String.format("customer with account number %s not found", ACCOUNT_NUMBER);

        assertThat(error.getMessage()).isEqualTo(expectedMessage);
    }

    @Test
    public void shouldReturnAccountNumber() {
        assertThat(error.getAccountNumber()).isEqualTo(ACCOUNT_NUMBER);
    }

}
