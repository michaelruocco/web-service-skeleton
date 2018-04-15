package uk.co.mruoc.app.controllers;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import uk.co.mruoc.api.ErrorDto;
import uk.co.mruoc.api.ErrorDto.ErrorDtoBuilder;
import uk.co.mruoc.app.controllers.ErrorResponse;

import static org.assertj.core.api.Assertions.assertThat;

public class ErrorResponseTest {

    private static final ErrorDto ERROR = new ErrorDtoBuilder().setMessage("my error message").build();
    private static final HttpStatus STATUS = HttpStatus.NOT_FOUND;

    @Test
    public void shouldReturnResponseWithErrorMessageInBody() {
        ErrorResponse response = new ErrorResponse(ERROR, STATUS);

        assertThat(response.getMessage()).isEqualTo(ERROR.getMessage());
    }

    @Test
    public void shouldReturnHttpStatusCode() {
        ErrorResponse response = new ErrorResponse(ERROR, STATUS);

        assertThat(response.getStatusCode()).isEqualTo(STATUS);
    }

    @Test
    public void shouldReturnHttpStatusCodeValue() {
        ErrorResponse response = new ErrorResponse(ERROR, STATUS);

        assertThat(response.getStatusCodeValue()).isEqualTo(STATUS.value());
    }

}
