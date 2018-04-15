package uk.co.mruoc.app;

import org.junit.Test;
import uk.co.mruoc.app.controllers.CustomerNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerNotFoundExceptionTest {

    @Test
    public void shouldReturnMessage() {
        String accountNumber = "9999999999";

        Throwable exception = new CustomerNotFoundException(accountNumber);

        assertThat(exception.getMessage()).isEqualTo(accountNumber);
    }

}
