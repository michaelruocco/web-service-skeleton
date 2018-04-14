package uk.co.mruoc.app;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RestExceptionHandlerTest {

    private final RestExceptionHandler handler = new RestExceptionHandler();

    @Test
    public void shouldReturnErrorResponseWithExceptionMessage() {
        String accountNumber = "999999999";
        String expectedMessage = String.format("customer with account number %s not found", accountNumber);
        CustomerNotFoundException cause = new CustomerNotFoundException(accountNumber);

        ErrorResponse response = handler.handleNotFound(cause);

        assertThat(response.getMessage()).isEqualTo(expectedMessage);
    }

}
