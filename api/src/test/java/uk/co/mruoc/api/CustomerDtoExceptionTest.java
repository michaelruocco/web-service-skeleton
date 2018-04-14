package uk.co.mruoc.api;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerDtoExceptionTest {

    @Test
    public void shouldReturnCause() {
        Throwable cause = new Exception();

        CustomerDtoException exception = new CustomerDtoException(cause);

        assertThat(exception.getCause()).isEqualTo(cause);
    }

}
