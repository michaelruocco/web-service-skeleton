package uk.co.mruoc.api;

import org.junit.Test;
import uk.co.mruoc.api.ErrorDto.ErrorDtoBuilder;

import static org.assertj.core.api.Assertions.assertThat;

public class ErrorDtoTest {

    private final ErrorDtoBuilder builder = new ErrorDtoBuilder();

    @Test
    public void shouldSetMessage() {
        String message = "my message";
        builder.setMessage(message);

        ErrorDto error = builder.build();

        assertThat(error.getMessage()).isEqualTo(message);
    }

}
