package uk.co.mruoc.client;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerClientExceptionTest {

    @Test
    public void shouldReturnMessage() {
        String message = "error message";

        Throwable exception = new CustomerClientException(message);

        assertThat(exception.getMessage()).isEqualTo(message);
    }

}
