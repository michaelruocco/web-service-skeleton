package uk.co.mruoc.app.model;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerAlreadyExistsExceptionTest {

    @Test
    public void shouldReturnMessage() {
        String accountNumber = "9999999999";

        Throwable exception = new CustomerAlreadyExistsException(accountNumber);

        assertThat(exception.getMessage()).isEqualTo(accountNumber);
    }

}
